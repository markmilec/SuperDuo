package barqsoft.footballscores.service;

import android.content.Intent;
import android.database.Cursor;
import android.os.Binder;
import android.widget.AdapterView;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import barqsoft.footballscores.DatabaseContract;
import barqsoft.footballscores.R;
import barqsoft.footballscores.Utilities;
import barqsoft.footballscores.ScoresAdapter;

/**
 * Created by Mark on 10/21/2015.
 */
public class WidgetRemoteViewsService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new RemoteViewsFactory() {
            private Cursor data = null;

            @Override
            public void onCreate() {}

            @Override
            public void onDataSetChanged() {
                final long identityToken = Binder.clearCallingIdentity();

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
                Date date = new Date(System.currentTimeMillis());
                String myDate = dateFormat.format(date);
                data = getContentResolver().query(DatabaseContract.scores_table
                        .buildScoreWithDate(), null, null, new String[]{myDate}, null);

                Binder.restoreCallingIdentity(identityToken);
            }

            @Override
            public void onDestroy() {
                if (data != null) {
                    data.close();
                    data = null;
                }
            }

            @Override
            public int getCount() {
                return data == null ? 0 : data.getCount();
            }

            @Override
            public RemoteViews getViewAt(int position) {
                if (position == AdapterView.INVALID_POSITION ||
                        data == null || !data.moveToPosition(position)) {
                    return null;
                }

                RemoteViews views = new RemoteViews(getPackageName(), R.layout.scores_widget_item);

                views.setTextViewText(R.id.home_name, data.getString(ScoresAdapter.COL_HOME));
                views.setTextViewText(R.id.away_name, data.getString(ScoresAdapter.COL_AWAY));
                views.setTextViewText(R.id.data_textview, data.getString(ScoresAdapter.COL_MATCHTIME));
                views.setTextViewText(R.id.score_textview, Utilities.getScores(
                        data.getInt(ScoresAdapter.COL_HOME_GOALS),
                        data.getInt(ScoresAdapter.COL_AWAY_GOALS)));

                views.setOnClickFillInIntent(R.id.scores_list_item, new Intent());

                return views;
            }

            @Override
            public RemoteViews getLoadingView() {
                return new RemoteViews(getPackageName(), R.layout.scores_widget_item);
            }

            @Override
            public int getViewTypeCount() {
                return 1;
            }

            @Override
            public long getItemId(int position) {
                if (data.moveToPosition(position)) {
                    return data.getLong(ScoresAdapter.COL_ID);
                }
                return position;
            }

            @Override
            public boolean hasStableIds() {
                return true;
            }
        };
    }
}