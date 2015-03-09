package course.labs.activitylab.tests;

import android.widget.Button;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.annotation.Config;
import org.robolectric.util.ActivityController;

import course.labs.activitylab.ActivityOne;
import course.labs.activitylab.R;
import course.labs.activitylab.RobolectricGradleTestRunner;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by icabanas on 25/02/15.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(emulateSdk = 18, reportSdk = 18)
public class StartActivityTwoTest {

    private ActivityOne mActivityOne;
    private ActivityController<ActivityOne> activityController;

    @Before
    public void setUp(){

        activityController = Robolectric.buildActivity(ActivityOne.class);

    }

    @Test
    public void should_update_activity_one__lifecycle_counters_when_activity_two_starts(){

        // GIVEN
        String expectedTxtCreate = "onCreate() calls: 1";
        String expectedTxtStart = "onStart() calls: 1";
        String expectedTxtResume = "onResume() calls: 1";
        String expectedTxtRestart = "onRestart() calls: 0";

        // obtengo la actividad uno
        mActivityOne = activityController.create().start().resume().visible().get();

        // obtengo el widget del botón que arranca la actividad dos
        Button mStartActivityTwoButton = (Button) mActivityOne.findViewById(R.id.bLaunchActivityTwo);

        // WHEN
        // arranco la actividad dos haciendo click sobre el botón
        mStartActivityTwoButton.performClick();

        // THEN
        TextView mTvCreate = (TextView) mActivityOne.findViewById(R.id.create);
        String actualTxtCreate = mTvCreate.getText().toString();
        assertThat("El número de invocaciones sobre el método onCreate de ActivityOne debería ser " +
                expectedTxtCreate, actualTxtCreate, is(equalTo(expectedTxtCreate)));

        TextView mTvStart = (TextView) mActivityOne.findViewById(R.id.start);
        String actualTxtStart = mTvStart.getText().toString();
        assertThat("El número de invocaciones sobre el método onStart de ActivityOne debería ser " +
                expectedTxtStart, actualTxtStart, is(equalTo(expectedTxtStart)));

        TextView mTvResume = (TextView) mActivityOne.findViewById(R.id.resume);
        String actualTxtResume = mTvResume.getText().toString();
        assertThat("El número de invocaciones sobre el método onResume de ActivityOne debería ser " +
                expectedTxtResume, actualTxtResume, is(equalTo(expectedTxtResume)));

        TextView mTvRestart = (TextView) mActivityOne.findViewById(R.id.restart);
        String actualTxtRestart = mTvRestart.getText().toString();
        assertThat("El número de invocaciones sobre el método onCreate de ActivityOne debería ser " +
                expectedTxtRestart, actualTxtRestart, is(equalTo(expectedTxtRestart)));

    }
}
