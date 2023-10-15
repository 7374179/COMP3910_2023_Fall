package ca.bcit.infosys.timesheet;

import java.util.ArrayList;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

import ca.bcit.infosys.employee.Employee;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;


@Named
@ApplicationScoped
public class TimesheetManager {
	private Employee e = new Employee();    
	private Timesheet emptyTimesheet = new Timesheet();	
	private List<Timesheet> timesheets;
	
	LocalDate aFriday = LocalDate.of(2014, 10, 10);
	LocalDate aThursday = LocalDate.of(2014, 10, 9);
	LocalDate aSaturday = LocalDate.of(2014, 10, 11);

	private Timesheet noDetails = new Timesheet(e, aFriday);
	private List<TimesheetRow> tsrsBig = new ArrayList<TimesheetRow>();
	
    private TimesheetManager() {
        timesheets = new ArrayList<>();
        Setup();
    }
    
    public void addRowtoUser() {
        tsrsBig.add(new TimesheetRow());
    }
    
    public void deleteRow(TimesheetRow rowToRemove) {
        tsrsBig.remove(rowToRemove);
    }
   
    
    public int getTotalDecihoursForDay(int day) {
        int total = 0;
        for (TimesheetRow row : tsrsBig) {
            total += row.getDecihour(day);
        }
        return total;
    }
    
    public float getTotalHoursForDay(int day) {
        float total = 0;
        for (TimesheetRow row : tsrsBig) {
            total += row.getHours()[day];
        }
        return total;
    }
    
    public Float getTotalSum() {
        float total = 0;
        for (TimesheetRow row : tsrsBig) {
            total += row.getSum();
        }
        return total;
    }       
    



    final void Setup() {
        tsrsBig.add(new TimesheetRow(123, "123", "comment1", new float[]{8f, 2f, 2.3f, 2.7f, 4f, 4f, 7f}));
        tsrsBig.add(new TimesheetRow(123, "123", "comment2", new float[]{0f, 0f, 8.04f, 8f, 4f, 4f, 8f}));
        tsrsBig.add(new TimesheetRow(123, "123", "comment3", new float[]{0f, 0f, 8f, 8f, 4f, 4f, 8f}));
        tsrsBig.add(new TimesheetRow());
        tsrsBig.add(new TimesheetRow());
    }
    
    public void setHourAtIndex(int rowIdx, int hourIdx, float charge) {
        if (charge < 0.0 || charge > Timesheet.HOURS_IN_DAY) {
            throw new IllegalArgumentException("charge is out of maximum hours in day range");
        }
        // Get the specific TimesheetRow based on rowIdx
        TimesheetRow row = tsrsBig.get(rowIdx);
        // Set the hour at the specified hourIdx
        row.getHours()[hourIdx] = charge;
    }

    public List<Timesheet> getTimesheets() {
        return timesheets;
    }
    
    public Employee getEmployee() {
        return e;
    }
    
    public Timesheet getEmptyTimesheet() {
        return emptyTimesheet;
    }

    public LocalDate getAFriday() {
        return aFriday;
    }
    
    public LocalDate getAThursday() {
        return aThursday;
    }

    public LocalDate getASaturday() {
        return aSaturday;
    }

    public Timesheet getNoDetails() {
        return noDetails;
    }

    public List<TimesheetRow> getTsrsBig() {
        return tsrsBig;
    }


}
