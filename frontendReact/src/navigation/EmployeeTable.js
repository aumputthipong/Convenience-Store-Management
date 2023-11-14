import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';
import AddEmployeeForm from './AddEmployeeForm';
import './EmployeeTable.css';
const EmployeeTable = () => {
    const token = localStorage.getItem('token');
    const [employees, setEmployees] = useState([]);
    const [searchTerm, setSearchTerm] = useState('');

  // Define the handleAddEmployee function
  const handleAddEmployee = (newEmployee) => {
    setEmployees((prevEmployees) => [...prevEmployees, newEmployee]);
  };

  const handleSearch = async () => {
    try {
      const response = await axios.get(`http://localhost:8082/employee-service/employees/searchPosition/${searchTerm}`);
      setEmployees(response.data);
    } catch (error) {
      console.error('Error searching employees:', error);
    }
  };
    useEffect(() => {
      const fetchEmployees = async () => {
        
        try {
          const response = await axios.get('http://localhost:8082/employee-service/employees/getAll');
          setEmployees(response.data);
        } catch (error) {
          console.error('Error fetching employees:', error);
        }
      };
  
      fetchEmployees();
    }, []);
  

    useEffect(() => {
        const fetchEmployees = async () => {
          try {
            let url = 'http://localhost:8082/employee-service/employees/getAll';
    
            // If searchTerm is not empty, append it to the URL for searching
            if (searchTerm) {
              url = `http://localhost:8082/employee-service/employees/searchPosition/${searchTerm}`;
            }
    
            const response = await axios.get(url);
            setEmployees(response.data);
          } catch (error) {
            console.error('Error fetching employees:', error);
          }
        };
    
        fetchEmployees();
      }, [searchTerm]);
    
    return (
        <div className="employee-table-container">
              <div className="search-bar">
       <input
          type="text"
          placeholder="กรองตำแหน่ง"
          value={searchTerm}
          onChange={(e) => setSearchTerm(e.target.value)}
        />
      </div>
        <div className="employee-table">
        
        <h2>รายชื่อพนักงาน</h2>
     
        <table>
          <thead>
            <tr>
              <th>รหัสพนักงาน</th>
              <th>ชื่อจริง</th>
              <th>นามสกุล</th>
              <th>วันเกิด</th>
              <th>ตำแหน่ง</th>
              <th>เงินเดือน</th>
              <th>วันลา</th>
              <th>วันขาด</th>
              <th></th>
            </tr>
          </thead>
          <tbody>
            {employees.map((employee) => (
              <tr key={employee.employeeId}>
                <td>{employee.employeeId}</td>
                <td>{employee.firstName}</td>
                <td>{employee.lastName}</td>
                <td>{employee.dob}</td>
                <td>{employee.position}</td>
                <td>{employee.salary}</td>
                <td>{employee.leaveCount}</td>
                <td>{employee.lateCount}</td>
                <td>
                  <Link to={`/employee/${employee.employeeId}`}>ดูรายละเอียด</Link>
                  
                </td>
                
              </tr>
            ))}
          </tbody>
        </table>
        </div>
      <div className="add-employee-form">
        <AddEmployeeForm onAddEmployee={handleAddEmployee} />
      </div>
    </div>
    );
  };
  
  export default EmployeeTable;

  