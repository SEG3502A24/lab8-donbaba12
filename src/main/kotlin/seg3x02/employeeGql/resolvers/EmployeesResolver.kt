package seg3x02.employeeGql.resolvers

import org.springframework.stereotype.Controller
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.stereotype.Component
import seg3x02.employeeGql.entity.Employee
import seg3x02.employeeGql.repository.EmployeesRepository

@Component
class EmployeeResolver(private val employeeRepository: EmployeeRepository) {

    @QueryMapping
    fun employees(): List<Employee> = employeeRepository.findAll()

    @QueryMapping
    fun employee(id: String): Employee? = employeeRepository.findById(id)

    @MutationMapping
    fun addEmployee(name: String, dateOfBirth: String, city: String, salary: Float, gender: String, email: String): Employee {
        val newEmployee = Employee(
            id = (employeeRepository.count() + 1).toString(),
            name = name,
            dateOfBirth = dateOfBirth,
            city = city,
            salary = salary,
            gender = gender,
            email = email
        )
        return employeeRepository.save(newEmployee)
    }
}