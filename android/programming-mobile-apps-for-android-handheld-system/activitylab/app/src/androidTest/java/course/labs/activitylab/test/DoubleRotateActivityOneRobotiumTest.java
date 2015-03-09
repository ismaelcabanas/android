package course.labs.activitylab.test;

import android.content.pm.ActivityInfo;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

import com.robotium.solo.Solo;

import course.labs.activitylab.ActivityOne;
import course.labs.activitylab.R;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by icabanas on 25/02/15.
 */
public class DoubleRotateActivityOneRobotiumTest extends ActivityInstrumentationTestCase2<ActivityOne> {

    Solo solo;

    public DoubleRotateActivityOneRobotiumTest(Class<ActivityOne> activityClass) {
        super(activityClass);
    }

    public DoubleRotateActivityOneRobotiumTest(){
        super(ActivityOne.class);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();

        solo.finishOpenedActivities();

    }

    public void test_double_rotate_activity_one(){

        // GIVEN
        String expectedTxtCreate = "onCreate() calls: 3";
        String expectedTxtStart = "onStart() calls: 3";
        String expectedTxtResume = "onResume() calls: 3";
        String expectedTxtRestart = "onRestart() calls: 0";

        solo = new Solo(getInstrumentation(), getActivity());
        solo.waitForActivity(ActivityOne.class);

        // WHEN
        // roto la pantalla a modo horizontal
        solo.setActivityOrientation(Solo.LANDSCAPE);

        solo.waitForActivity(ActivityOne.class);

        // roto la pantalla a modo vertical
        solo.setActivityOrientation(Solo.PORTRAIT);

        solo.waitForActivity(ActivityOne.class);

        // THEN
        // compruebo que la actividad es ActivityOne
        solo.assertCurrentActivity("La actividad debería ser ActivityOne", ActivityOne.class);


        TextView mTvCreate = (TextView) solo.getView(R.id.create);
        String actualTxtCreate = mTvCreate.getText().toString();
        assertThat("El número de invocaciones sobre el método onCreate de ActivityOne debería ser " +
                expectedTxtCreate, actualTxtCreate, is(equalTo(expectedTxtCreate)));

        TextView mTvStart = (TextView) solo.getView(R.id.start);
        String actualTxtStart = mTvStart.getText().toString();
        assertThat("El número de invocaciones sobre el método onStart de ActivityOne debería ser " +
                expectedTxtStart, actualTxtStart, is(equalTo(expectedTxtStart)));

        TextView mTvResume = (TextView) solo.getView(R.id.resume);
        String actualTxtResume = mTvResume.getText().toString();
        assertThat("El número de invocaciones sobre el método onResume de ActivityOne debería ser " +
                expectedTxtResume, actualTxtResume, is(equalTo(expectedTxtResume)));

        TextView mTvRestart = (TextView) solo.getView(R.id.restart);
        String actualTxtRestart = mTvRestart.getText().toString();
        assertThat("El número de invocaciones sobre el método onCreate de ActivityOne debería ser " +
                expectedTxtRestart, actualTxtRestart, is(equalTo(expectedTxtRestart)));
    }
}
