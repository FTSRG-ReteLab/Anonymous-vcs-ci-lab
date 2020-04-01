package hu.bme.mit.train.sensor;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class TrainSensorTest {

    TrainUser mockTU;
    TrainController mockTC;
    private TrainSensor ts;


    @Before
        public void init() {
        mockTU = mock(TrainUser.class);
        mockTC= mock(TrainController.class);
        ts = new TrainSensorImpl(mockTC,mockTU);
    }

    @Test
    public void DoNotAlertAtSpeedLimit500() {
        // Arrange
        // Act
        ts.overrideSpeedLimit(500);
        // Assert
        verify(mockTU, times(1)).setAlarmState(false);
    }

    @Test
    public void AlertAtSpeedLimit501() {
        // Arrange
        // Act
        ts.overrideSpeedLimit(501);
        // Assert
        verify(mockTU, times(1)).setAlarmState(true);
    }

    @Test
    public void AlertAtSpeedLimitLessThanHalf() {
        // Arrange
        // Act
        ts.overrideSpeedLimit(1);
        // Assert
        verify(mockTU, times(1)).setAlarmState(true);
    }

    @Test
    public void DoNotAlertAtSpeedLimitMoreThanHalf() {
        // Arrange
        // Act
        ts.overrideSpeedLimit(10);
        // Assert
        verify(mockTU, times(1)).setAlarmState(false);
    }

    @Test
    public void AlertAtNegativeSpeedLimit() {
        // Arrange
        // Act
        ts.overrideSpeedLimit(-1);
        // Assert
        verify(mockTU, times(1)).setAlarmState(true);
    }
}
