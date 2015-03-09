package course.labs.activitylab.test;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

import com.robotium.solo.Solo;

import org.junit.Test;

import course.labs.activitylab.ActivityOne;
import course.labs.activitylab.R;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by icabanas on 10/02/15.
 */
public class StartActivityOneRobotiumTest extends ActivityInstrumentationTestCase2<ActivityOne> {

    Solo solo;

    public StartActivityOneRobotiumTest() {
        super(ActivityOne.class);
    }

    @Override
    protected void setUp() throws Exception {

    }

    @Override
    protected void tearDown() throws Exception {
        solo.finishOpenedActivities();
    }

    public void test_start_activity_one(){

        // GIVEN
        String expectedTxtCreate = "onCreate() calls: 1";
        String expectedTxtStart = "onStart() calls: 1";
        String expectedTxtResume = "onResume() calls: 1";
        String expectedTxtRestart = "onRestart() calls: 0";

        // WNEN
        solo = new Solo(getInstrumentation(), getActivity());

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
