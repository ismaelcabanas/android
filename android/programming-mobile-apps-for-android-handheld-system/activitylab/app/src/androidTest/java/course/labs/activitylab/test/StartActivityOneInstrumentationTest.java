package course.labs.activitylab.test;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

import course.labs.activitylab.ActivityOne;
import course.labs.activitylab.R;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by icabanas on 1/02/15.
 */
public class StartActivityOneInstrumentationTest extends ActivityInstrumentationTestCase2<ActivityOne> {

    private ActivityOne mActivityOne;

    public StartActivityOneInstrumentationTest(Class<ActivityOne> activityClass) {
        super(activityClass);
    }

    // El constructor por defecto es necesario para que se ejecuten los tests, de lo contrario, al
    // ejecutar el los tests en AS nos dirá que Empty Test Suite
    public StartActivityOneInstrumentationTest() {
        super(ActivityOne.class);
    }

    @Override
    protected void setUp() throws Exception {

        super.setUp();


    }


    @Override
    protected void tearDown() throws Exception {
        super.tearDown();

        // finalizo la actividad, de esta forma aseguramos que se invoquen los métodos onPause(),
        // onStop() y onDestroy() del ciclo de vida de la actividad
        setActivity(null);
        //mActivityOne.finish();
    }

    public void test_start_activity_one(){

        // GIVEN
        String expectedTxtCreate = "onCreate() calls: 1";
        String expectedTxtStart = "onStart() calls: 1";
        String expectedTxtResume = "onResume() calls: 1";
        String expectedTxtRestart = "onRestart() calls: 0";

        // WNEN
        mActivityOne = getActivity();

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
