package course.labs.activitylab.tests;

import android.content.res.Configuration;
import android.os.Bundle;
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
 * Created by icabanas on 24/02/15.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(emulateSdk = 18, reportSdk = 18)
public class DoubleRotateActivityOneTest {

    private ActivityOne mActivityOne;
    private ActivityController<ActivityOne> activityController;

    @Before
    public void setUp(){

        activityController = Robolectric.buildActivity(ActivityOne.class);

    }

    @Test
    public void should_update_lifecycle_counters_when_double_rotate_activity_one(){

        // GIVEN
        String expectedTxtCreate = "onCreate() calls: 3";
        String expectedTxtStart = "onStart() calls: 3";
        String expectedTxtResume = "onResume() calls: 3";
        String expectedTxtRestart = "onRestart() calls: 0";

        // inicio ActivityOne
        mActivityOne = activityController.create().start().resume().visible().get();

        // WHEN
        // roto el dispositivo a modo landscape
        Robolectric.application.getResources().getConfiguration().orientation = Configuration.ORIENTATION_LANDSCAPE;
        Bundle bundle = new Bundle();
        activityController.saveInstanceState(bundle).pause().stop().destroy();
        ActivityController<ActivityOne> controller = Robolectric.buildActivity(ActivityOne.class).create(bundle).start().restoreInstanceState(bundle).resume();

        // roto el dispositivo a modo portrait
        Robolectric.application.getResources().getConfiguration().orientation = Configuration.ORIENTATION_PORTRAIT;
        bundle = new Bundle();
        controller.saveInstanceState(bundle).pause().stop().destroy();
        ActivityController<ActivityOne> otherController = Robolectric.buildActivity(ActivityOne.class).create(bundle).start().restoreInstanceState(bundle).resume();

        mActivityOne = otherController.visible().get();

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
