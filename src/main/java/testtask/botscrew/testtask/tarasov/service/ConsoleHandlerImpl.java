package testtask.botscrew.testtask.tarasov.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;

@Service
@RequiredArgsConstructor
public class ConsoleHandlerImpl implements ConsoleHandler {
    private final BufferedReader reader;
    private final CommandHandler handler;

    @Override
    @SneakyThrows
    public void readQuery() {
        var line = "";
        printMenu();
        while (!(line = reader.readLine()).equals("exit")) handleQuery(line);
    }

    private void handleQuery(String line) {
        if (line.equals("menu")) printMenu();
        else handler.execute(line);
    }

    public void printMenu() {
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
                """;
        System.out.println(menu);
    }
}
