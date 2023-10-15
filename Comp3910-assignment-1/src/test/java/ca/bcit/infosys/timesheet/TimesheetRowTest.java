package ca.bcit.infosys.timesheet;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;

import static ca.bcit.infosys.timesheet.TimesheetRow.toDecihour;
import static ca.bcit.infosys.timesheet.TimesheetRow.toHour;
import static ca.bcit.infosys.timesheet.TimesheetRow.SAT;
import static ca.bcit.infosys.timesheet.TimesheetRow.SUN;
import static ca.bcit.infosys.timesheet.TimesheetRow.MON;
import static ca.bcit.infosys.timesheet.TimesheetRow.TUE;
import static ca.bcit.infosys.timesheet.TimesheetRow.WED;
import static ca.bcit.infosys.timesheet.TimesheetRow.THU;
import static ca.bcit.infosys.timesheet.TimesheetRow.FRI;
/**
 * JUnit tests for TimesheetRow.
 *
 * @author blink
 * @version 2.0
 */
class TimesheetRowTest {
    private TimesheetRow tr = new TimesheetRow();
    private final float bd = 2.3f;
    private final int bdeci = 23;
    
  //totals 30 hours, order SAT to FRI
    private final float[] hours = {8f, 2f, 2.3f, 2.7f, 4f, 4f, 7f}; 
    private final int[] decihours = {80, 20, 23, 27, 40, 40, 70}; 
    private final long packedHours = 0x4628281B171450L;
    //totals 32 hours
    private final float[] hours2 = {0f, 0f, 8f, 8f, 4f, 4f, 8f};
    private final int[] decihours2 = {0, 0, 80, 80, 40, 40, 80};
    private final long packedHours2 = 0x50282850500000L;
    
    //totals 33 hours
    private final float[] hours3 = {0.1f, 0.1f, 8.2f, 8.2f, 4.4f, 4f, 8f}; 
    private final int[] decihours3 = {1, 1, 82, 82, 44, 40, 80}; 
    private final long packedHours3 = 0x50282C52520101L;

    @BeforeEach
    void setUp() throws Exception {
        tr.setProjectId(123);
        tr.setWorkPackageId("A123");
    }

    @Test
    final void testTimesheetRow() {
        TimesheetRow tr = new TimesheetRow();
        assertEquals(0, tr.getProjectId());
        assertNull(tr.getWorkPackageId());
        assertEquals(0, tr.getPackedHours());
        assertNull(tr.getNotes());
    }

    @Test
    final void testTimesheetRowIntString() {
        TimesheetRow tr = new TimesheetRow(123, "12300");
        assertEquals(123, tr.getProjectId());
        assertSame("12300", tr.getWorkPackageId());
        assertEquals(0, tr.getPackedHours());
        assertNull(tr.getNotes());
    }

    @Test
    final void testTimesheetRowIntStringStringFloatArray() {
        TimesheetRow tr = new TimesheetRow(1234, "1234", "comments", hours);
        assertEquals(1234, tr.getProjectId());
        assertEquals("1234", tr.getWorkPackageId());
        assertEquals("comments", tr.getNotes());
        assertArrayEquals(hours, tr.getHours());
        assertEquals(8f, tr.getHour(TimesheetRow.SAT));
        assertEquals(2f, tr.getHour(TimesheetRow.SUN));
        assertEquals(2.3f, tr.getHour(TimesheetRow.MON));
        assertEquals(2.7f, tr.getHour(TimesheetRow.TUE));
        assertEquals(4f, tr.getHour(TimesheetRow.WED));
        assertEquals(4f, tr.getHour(TimesheetRow.THU));
        assertEquals(7f, tr.getHour(TimesheetRow.FRI));
        
        tr = new TimesheetRow(1234, "1234", "comments", hours2);
        assertEquals(1234, tr.getProjectId());
        assertEquals("1234", tr.getWorkPackageId());
        assertEquals("comments", tr.getNotes());
        assertArrayEquals(hours2, tr.getHours());
        assertEquals(0f, tr.getHour(TimesheetRow.SAT));
        assertEquals(0f, tr.getHour(TimesheetRow.SUN));
        assertEquals(8f, tr.getHour(TimesheetRow.MON));
        assertEquals(8f, tr.getHour(TimesheetRow.TUE));
        assertEquals(4f, tr.getHour(TimesheetRow.WED));
        assertEquals(4f, tr.getHour(TimesheetRow.THU));
        assertEquals(8f, tr.getHour(TimesheetRow.FRI));

        tr = new TimesheetRow(1234, "1234", "comments", hours3);
        assertEquals(1234, tr.getProjectId());
        assertEquals("1234", tr.getWorkPackageId());
        assertEquals("comments", tr.getNotes());
        assertArrayEquals(hours3, tr.getHours());
        assertEquals(.1f, tr.getHour(TimesheetRow.SAT));
        assertEquals(.1f, tr.getHour(TimesheetRow.SUN));
        assertEquals(8.2f, tr.getHour(TimesheetRow.MON));
        assertEquals(8.2f, tr.getHour(TimesheetRow.TUE));
        assertEquals(4.4f, tr.getHour(TimesheetRow.WED));
        assertEquals(4f, tr.getHour(TimesheetRow.THU));
        assertEquals(8f, tr.getHour(TimesheetRow.FRI));
    }

    @Test
    final void testToDecihour() {
        assertEquals(0, toDecihour(0f));
        assertEquals(10, toDecihour(1f));
        assertEquals(23, toDecihour(2.3f));
        assertEquals(23, toDecihour(2.3333333f));
        assertEquals(24, toDecihour(2.3555555f));
        assertEquals(2413, toDecihour(241.3f));
    }

    @Test
    final void testToHour() {
        assertEquals(0f, toHour(0));
        assertEquals(1f, toHour(10));
        assertEquals(2.3f, toHour(23));
        assertEquals(241.3f, toHour(2413));
        assertEquals(-1.3f, toHour(-13));
        assertEquals(123456.7f, toHour(1234567));
    }

    @Test
    final void testGetProjectId() {
        assertEquals(123, tr.getProjectId());
    }

    @Test
    final void testSetProjectId() {
        tr.setProjectId(2341);
        assertEquals(2341, tr.getProjectId());
        assertThrows(IllegalArgumentException.class,
                () -> tr.setProjectId(-1));
        tr.setProjectId(0);
        assertEquals(0, tr.getProjectId());        
    }

    @Test
    final void testGetWorkPackageId() {
        assertSame("A123", tr.getWorkPackageId());
    }

    @Test
    final void testSetWorkPackageId() {
        tr.setWorkPackageId("12300");
        assertSame("12300", tr.getWorkPackageId());
    }

    @Test
    final void testGetPackedHours() {
        assertEquals(0, tr.getPackedHours());
        tr.setPackedHours(0x123456789AL);
        assertEquals(0x123456789AL, tr.getPackedHours());
    }

    @Test
    final void testSetPackedHours() {
        tr.setPackedHours(0x123456789AL);
        assertEquals(0x123456789AL, tr.getPackedHours());
        tr.setPackedHours(0x01010101010101L);
        assertEquals(0x01010101010101L, tr.getPackedHours());
        tr.setPackedHours(0xF0F0F0F0F0F0F0L);
        assertEquals(0xF0F0F0F0F0F0F0L, tr.getPackedHours());
        tr.setPackedHours(0L);
        assertEquals(0L, tr.getPackedHours());
        tr.setPackedHours(0);
        assertEquals(0L, tr.getPackedHours());

        //largest valid byte is 240 == 0xF0
        assertThrows(IllegalArgumentException.class,
                () -> tr.setPackedHours(0xAA01010101010101L));
        assertThrows(IllegalArgumentException.class,
                () -> tr.setPackedHours(0xF1F0F0F0F0F0F0L));
        assertThrows(IllegalArgumentException.class,
                () -> tr.setPackedHours(0xF0F1F0F0F0F0F0L)); 
        assertThrows(IllegalArgumentException.class,
                () -> tr.setPackedHours(0xF0F0F1F0F0F0F0L));
        assertThrows(IllegalArgumentException.class,
                () -> tr.setPackedHours(0xF0F0F0F1F0F0F0L));
        assertThrows(IllegalArgumentException.class,
                () -> tr.setPackedHours(0xF0F0F0F0F1F0F0L));
        assertThrows(IllegalArgumentException.class,
                () -> tr.setPackedHours(0xF0F0F0F0F0F1F0L));
        assertThrows(IllegalArgumentException.class,
                () -> tr.setPackedHours(0xF0F0F0F0F0F0F1L));  
    }

    @Test
    final void testGetHour() {
        tr.setPackedHours(0x07060504030201L);
        assertEquals(.1f, tr.getHour(SAT));
        assertEquals(.2f, tr.getHour(SUN));
        assertEquals(.3f, tr.getHour(MON));
        assertEquals(.4f, tr.getHour(TUE));
        assertEquals(.5f, tr.getHour(WED));
        assertEquals(.6f, tr.getHour(THU));
        assertEquals(.7f, tr.getHour(FRI));
        tr.setPackedHours(0x463C32281E140AL);
        assertEquals(1f, tr.getHour(SAT));
        assertEquals(2f, tr.getHour(SUN));
        assertEquals(3f, tr.getHour(MON));
        assertEquals(4f, tr.getHour(TUE));
        assertEquals(5f, tr.getHour(WED));
        assertEquals(6f, tr.getHour(THU));
        assertEquals(7f, tr.getHour(FRI));
        assertThrows(IllegalArgumentException.class,
                () -> tr.getHour(-1));  
        assertThrows(IllegalArgumentException.class,
                () -> tr.getHour(7));  
    }

    @Test
    final void testSetHour() {
        tr.setPackedHours(0x463C32281E140AL);
        tr.setHour(MON, bd);
        assertEquals(bd, tr.getHour(MON));
        assertEquals(1f, tr.getHour(SAT));
        assertEquals(2f, tr.getHour(SUN));
        assertEquals(4f, tr.getHour(TUE));
        assertEquals(5f, tr.getHour(WED));
        assertEquals(6f, tr.getHour(THU));
        assertEquals(7f, tr.getHour(FRI));

        tr.setHour(MON, 24f);
        assertEquals(24f, tr.getHour(MON));
        tr.setHour(MON, 24f);
        assertEquals(24f, tr.getHour(MON));
        
        
        tr.setHour(TimesheetRow.SAT, 0f);
        assertEquals(0f, tr.getHour(TimesheetRow.SAT));
        
        tr.setPackedHours(0x463C32281E140AL);
        tr.setHour(TUE, bd);
        assertEquals(bd, tr.getHour(TUE));
        assertEquals(1f, tr.getHour(SAT));
        assertEquals(2f, tr.getHour(SUN));
        assertEquals(3f, tr.getHour(MON));
        assertEquals(5f, tr.getHour(WED));
        assertEquals(6f, tr.getHour(THU));
        assertEquals(7f, tr.getHour(FRI));

        tr.setPackedHours(0x463C32281E140AL);
        tr.setHour(WED, bd);
        assertEquals(bd, tr.getHour(WED));
        assertEquals(1f, tr.getHour(SAT));
        assertEquals(2f, tr.getHour(SUN));
        assertEquals(3f, tr.getHour(MON));
        assertEquals(4f, tr.getHour(TUE));
        assertEquals(6f, tr.getHour(THU));
        assertEquals(7f, tr.getHour(FRI));

        tr.setPackedHours(0x463C32281E140AL);
        tr.setHour(THU, bd);
        assertEquals(bd, tr.getHour(THU));
        assertEquals(1f, tr.getHour(SAT));
        assertEquals(2f, tr.getHour(SUN));
        assertEquals(3f, tr.getHour(MON));
        assertEquals(4f, tr.getHour(TUE));
        assertEquals(5f, tr.getHour(WED));
        assertEquals(7f, tr.getHour(FRI));

        tr.setPackedHours(0x463C32281E140AL);
        tr.setHour(FRI, bd);
        assertEquals(bd, tr.getHour(FRI));
        assertEquals(1f, tr.getHour(SAT));
        assertEquals(2f, tr.getHour(SUN));
        assertEquals(3f, tr.getHour(MON));
        assertEquals(4f, tr.getHour(TUE));
        assertEquals(5f, tr.getHour(WED));
        assertEquals(6f, tr.getHour(THU));

        tr.setPackedHours(0x463C32281E140AL);
        tr.setHour(SAT, bd);
        assertEquals(bd, tr.getHour(SAT));
        assertEquals(2f, tr.getHour(SUN));
        assertEquals(3f, tr.getHour(MON));
        assertEquals(4f, tr.getHour(TUE));
        assertEquals(5f, tr.getHour(WED));
        assertEquals(6f, tr.getHour(THU));
        assertEquals(7f, tr.getHour(FRI));

        tr.setPackedHours(0x463C32281E140AL);
        tr.setHour(SUN, bd);
        assertEquals(bd, tr.getHour(SUN));
        assertEquals(1f, tr.getHour(SAT));
        assertEquals(3f, tr.getHour(MON));
        assertEquals(4f, tr.getHour(TUE));
        assertEquals(5f, tr.getHour(WED));
        assertEquals(6f, tr.getHour(THU));
        assertEquals(7f, tr.getHour(FRI));

        //exceptions
        assertThrows(IllegalArgumentException.class,
                () -> tr.setHour(MON, 24.55f));
        assertThrows(IllegalArgumentException.class,
                () -> tr.setHour(MON, 25f));
        assertThrows(IllegalArgumentException.class,
                () -> tr.setHour(MON, -1f));
        assertThrows(IllegalArgumentException.class,
                () -> tr.setHour(MON, 24.1f));
    }

    @Test
    final void testGetNotes() {
        assertNull(tr.getNotes());
    }

    @Test
    final void testSetNotes() {
        tr.setNotes("This is a note");
        assertSame("This is a note", tr.getNotes());
    }

    @Test
    final void testGetSum() {
        tr.setHour(TimesheetRow.MON, bd);
        tr.setHour(TimesheetRow.SAT, 0f);
        assertEquals(bd, tr.getSum());
        tr.setHour(TimesheetRow.MON, 2.3f);
        assertEquals(2.3f, tr.getSum());
        tr.setHour(TimesheetRow.WED, 0f);
        assertEquals(bd, tr.getSum());

        tr.setHours(hours);
        assertEquals(30f, tr.getSum());

        tr.setHours(hours2);
        assertEquals(32f, tr.getSum());

        tr.setHours(hours3);
        assertEquals(33f, tr.getSum());
    }

    @Test
    final void testGetDeciSum() {
        tr.setHour(TimesheetRow.MON, bd);
        tr.setHour(TimesheetRow.SAT, 0f);
        assertEquals(bdeci, tr.getDeciSum());
        tr.setHour(TimesheetRow.MON, 2.3f);
        assertEquals(23, tr.getDeciSum());
        tr.setHour(TimesheetRow.WED, 0f);
        assertEquals(23, tr.getDeciSum());

        tr.setHours(hours);
        assertEquals(300, tr.getDeciSum());

        tr.setHours(hours2);
        assertEquals(320, tr.getDeciSum());

        tr.setHours(hours3);
        assertEquals(330, tr.getDeciSum());
    }

    @Test
    final void testGetDecihour() {
        tr.setPackedHours(0x07060504030201L);
        assertEquals(1, tr.getDecihour(SAT));
        assertEquals(2, tr.getDecihour(SUN));
        assertEquals(3, tr.getDecihour(MON));
        assertEquals(4, tr.getDecihour(TUE));
        assertEquals(5, tr.getDecihour(WED));
        assertEquals(6, tr.getDecihour(THU));
        assertEquals(7, tr.getDecihour(FRI));
        tr.setPackedHours(0x463C32281E140AL);
        assertEquals(10, tr.getDecihour(SAT));
        assertEquals(20, tr.getDecihour(SUN));
        assertEquals(30, tr.getDecihour(MON));
        assertEquals(40, tr.getDecihour(TUE));
        assertEquals(50, tr.getDecihour(WED));
        assertEquals(60, tr.getDecihour(THU));
        assertEquals(70, tr.getDecihour(FRI));
        assertThrows(IllegalArgumentException.class,
                () -> tr.getDecihour(-1));  
        assertThrows(IllegalArgumentException.class,
                () -> tr.getDecihour(7));  
    }

    @Test
    final void testSetDecihour() {
        tr.setDecihour(MON, bdeci);
        assertEquals(bdeci, tr.getDecihour(MON));

        tr.setDecihour(MON, 23);
        assertEquals(bdeci, tr.getDecihour(MON));

        tr.setDecihour(MON, 240);
        assertEquals(240, tr.getDecihour(MON));
        tr.setDecihour(SAT, 0);
        assertEquals(0, tr.getDecihour(SAT));
        
        tr.setPackedHours(0x463C32281E140AL);
        tr.setDecihour(SAT, 23);
        assertEquals(23, tr.getDecihour(SAT));
        assertEquals(20, tr.getDecihour(SUN));
        assertEquals(30, tr.getDecihour(MON));
        assertEquals(40, tr.getDecihour(TUE));
        assertEquals(50, tr.getDecihour(WED));
        assertEquals(60, tr.getDecihour(THU));
        assertEquals(70, tr.getDecihour(FRI));
        
        tr.setPackedHours(0x463C32281E140AL);
        tr.setDecihour(SUN, 23);
        assertEquals(23, tr.getDecihour(SUN));
        assertEquals(10, tr.getDecihour(SAT));
        assertEquals(30, tr.getDecihour(MON));
        assertEquals(40, tr.getDecihour(TUE));
        assertEquals(50, tr.getDecihour(WED));
        assertEquals(60, tr.getDecihour(THU));
        assertEquals(70, tr.getDecihour(FRI));
        
        tr.setPackedHours(0x463C32281E140AL);
        tr.setDecihour(MON, 23);
        assertEquals(23, tr.getDecihour(MON));
        assertEquals(10, tr.getDecihour(SAT));
        assertEquals(20, tr.getDecihour(SUN));
        assertEquals(40, tr.getDecihour(TUE));
        assertEquals(50, tr.getDecihour(WED));
        assertEquals(60, tr.getDecihour(THU));
        assertEquals(70, tr.getDecihour(FRI));
        
        tr.setPackedHours(0x463C32281E140AL);
        tr.setDecihour(TUE, 23);
        assertEquals(23, tr.getDecihour(TUE));
        assertEquals(10, tr.getDecihour(SAT));
        assertEquals(20, tr.getDecihour(SUN));
        assertEquals(30, tr.getDecihour(MON));
        assertEquals(50, tr.getDecihour(WED));
        assertEquals(60, tr.getDecihour(THU));
        assertEquals(70, tr.getDecihour(FRI));
        
        tr.setPackedHours(0x463C32281E140AL);
        tr.setDecihour(WED, 23);
        assertEquals(23, tr.getDecihour(WED));
        assertEquals(10, tr.getDecihour(SAT));
        assertEquals(20, tr.getDecihour(SUN));
        assertEquals(30, tr.getDecihour(MON));
        assertEquals(40, tr.getDecihour(TUE));
        assertEquals(60, tr.getDecihour(THU));
        assertEquals(70, tr.getDecihour(FRI));
        
        tr.setPackedHours(0x463C32281E140AL);
        tr.setDecihour(THU, 23);
        assertEquals(23, tr.getDecihour(THU));
        assertEquals(10, tr.getDecihour(SAT));
        assertEquals(20, tr.getDecihour(SUN));
        assertEquals(30, tr.getDecihour(MON));
        assertEquals(40, tr.getDecihour(TUE));
        assertEquals(50, tr.getDecihour(WED));
        assertEquals(70, tr.getDecihour(FRI));
        
        tr.setPackedHours(0x463C32281E140AL);
        tr.setDecihour(FRI, 23);
        assertEquals(23, tr.getDecihour(FRI));
        assertEquals(10, tr.getDecihour(SAT));
        assertEquals(20, tr.getDecihour(SUN));
        assertEquals(30, tr.getDecihour(MON));
        assertEquals(40, tr.getDecihour(TUE));
        assertEquals(50, tr.getDecihour(WED));
        assertEquals(60, tr.getDecihour(THU));

        //exceptions
        assertThrows(IllegalArgumentException.class,
                () -> tr.setDecihour(MON, 245));
        assertThrows(IllegalArgumentException.class,
                () -> tr.setDecihour(MON, 250));
        assertThrows(IllegalArgumentException.class,
                () -> tr.setDecihour(MON, -1));
        assertThrows(IllegalArgumentException.class,
                () -> tr.setDecihour(MON, 241));
    }

    @Test
    final void testGetHours() {
        float[] a1 = {.1f, .2f, .3f, .4f, .5f, .6f, .7f};
        float[] a2  = {1f, 2f, 3f, 4f, 5f, 6f, 7f};
        tr.setPackedHours(0x07060504030201L);
        assertArrayEquals(a1, tr.getHours());
        tr.setPackedHours(0x463C32281E140AL);
        assertArrayEquals(a2, tr.getHours());
        
        tr.setPackedHours(packedHours);
        assertArrayEquals(hours, tr.getHours());
        tr.setPackedHours(packedHours2);
        assertArrayEquals(hours2, tr.getHours());
        tr.setPackedHours(packedHours3);
        assertArrayEquals(hours3, tr.getHours());
    }

    @Test
    final void testSetHours() {
        float[] a1 = {.1f, .2f, .3f, .4f, .5f, .6f, .7f};
        float[] a2  = {1f, 2f, 3f, 4f, 5f, 6f, 7f};
        tr.setHours(a1);
        assertEquals(0x07060504030201L, tr.getPackedHours());
        tr.setHours(a2);
        assertEquals(0x463C32281E140AL, tr.getPackedHours());
        
        tr.setHours(hours);
        assertEquals(packedHours, tr.getPackedHours());
        tr.setHours(hours2);
        assertEquals(packedHours2, tr.getPackedHours());
        tr.setHours(hours3);
        assertEquals(packedHours3, tr.getPackedHours());
        
        final float[] hours = {8.1f, -2f, 2.3f, 2.7f, 4f, 4f, 7f};
        assertThrows(IllegalArgumentException.class,
                () -> tr.setHours(hours));
    }

    @Test
    final void testGetDecihours() {
        int[] a1 = {1, 2, 3, 4, 5, 6, 7};
        int[] a2  = {10, 20, 30, 40, 50, 60, 70};
        tr.setPackedHours(0x07060504030201L);
        assertArrayEquals(a1, tr.getDecihours());
        tr.setPackedHours(0x463C32281E140AL);
        assertArrayEquals(a2, tr.getDecihours());
        
        tr.setPackedHours(packedHours);
        assertArrayEquals(decihours, tr.getDecihours());
        tr.setPackedHours(packedHours2);
        assertArrayEquals(decihours2, tr.getDecihours());
        tr.setPackedHours(packedHours3);
        assertArrayEquals(decihours3, tr.getDecihours());
    }

    @Test
    final void testSetDecihours() {
        int[] a1 = {1, 2, 3, 4, 5, 6, 7};
        int[] a2  = {10, 20, 30, 40, 50, 60, 70};
        tr.setDecihours(a1);
        assertEquals(0x07060504030201L, tr.getPackedHours());
        tr.setDecihours(a2);
        assertEquals(0x463C32281E140AL, tr.getPackedHours());
        
        tr.setDecihours(decihours);
        assertEquals(packedHours, tr.getPackedHours());
        tr.setDecihours(decihours2);
        assertEquals(packedHours2, tr.getPackedHours());
        tr.setDecihours(decihours3);
        assertEquals(packedHours3, tr.getPackedHours());
        
        final int[] dh = {81, -2, 22, 25, 40, 40, 70};
        assertThrows(IllegalArgumentException.class,
                () -> tr.setDecihours(dh));
    }

    @Test
    final void testToString() {
        assertEquals("123 A123 [0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0]",
                tr.toString());
        tr.setDecihours(decihours);
        assertEquals("123 A123 [8.0, 2.0, 2.3, 2.7, 4.0, 4.0, 7.0]",
                tr.toString());
        tr.setDecihours(decihours2);
        assertEquals("123 A123 [0.0, 0.0, 8.0, 8.0, 4.0, 4.0, 8.0]",
                tr.toString());
        tr.setDecihours(decihours3);
        assertEquals("123 A123 [0.1, 0.1, 8.2, 8.2, 4.4, 4.0, 8.0]",
                tr.toString());
        
    }

}
