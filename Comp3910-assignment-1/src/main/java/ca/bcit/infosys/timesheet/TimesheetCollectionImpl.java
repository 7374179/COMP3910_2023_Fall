package ca.bcit.infosys.timesheet;

import java.util.List;
import java.util.stream.Collectors;

import ca.bcit.infosys.employee.Employee;

public class TimesheetCollectionImpl implements TimesheetCollection {

    private List<Timesheet> timesheets;

    @Override
    public List<Timesheet> getTimesheets() {
        return timesheets;
    }

    @Override
    public List<Timesheet> getTimesheets(Employee e) {
        return timesheets.stream()
                .filter(ts -> ts.getEmployee().equals(e))
                .collect(Collectors.toList());
    }

    @Override
    public Timesheet getCurrentTimesheet(Employee e) {
        // 여기에 해당 직원의 현재 timesheet를 반환하는 로직을 구현하세요.
        return null;
    }

    @Override
    public String addTimesheet() {
        // 여기에 새로운 timesheet를 추가하고 관련 페이지로의 네비게이션을 반환하는 로직을 구현하세요.
        return null;
    }
    


}
