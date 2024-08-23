package testtask.botscrew.testtask.tarasov.service.command;

import org.springframework.stereotype.Component;

@Component
public class PrintMenu implements Command {
    private final static String QUERY_PREFIX = "menu";
    @Override
    public void execute(String query) {
        var menu = """
                 Welcome to the University System!
                          
                    Available Commands:
                    1. Who is head of department {department_name}
                       - Answer: Head of {department_name} department is {head_of_department_name}                    
                    2. Show {department_name} statistics
                       - Answer: assistants - {assistants_count}
                         associate professors - {associate_professors_count}
                         professors - {professors_count}                                
                    3. Show the average salary for the department {department_name}
                       - Answer: The average salary of {department_name} is {average_salary}                                
                    4. Show count of employees for {department_name}
                       - Answer: {employee_count}                                
                    5. Global search by {template}
                       - Example: Global search by van
                       - Answer: Ivan Petrenko, Petro Ivanov
                    6. Show all lectors
                       - Answer: Lists all lectors in the system                       
                    7. Show all departments
                       - Answer: Lists all departments in the system
                    8. Add lector (name = {name}, degree = {degree}, salary = {salary})
                        !notice the salary is a long value!
                    9. Add department (name = {name}, headId = {head_id})
                    10. Show lectors by department {department_name}
                    11. Show departments by lector {lector_id}
                    12. menu
                    13. exit
                """;
        System.out.println(menu);
    }

    @Override
    public boolean isCommandFits(String query) {
        return query.contains(QUERY_PREFIX);
    }
}
