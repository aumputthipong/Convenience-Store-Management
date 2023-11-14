import React, { useState, useEffect } from "react";
import axios from "axios";
import { useParams } from 'react-router-dom';
import './UpdateForm.css';
const UpdateEmployeeForm = () => {
    const { id } = useParams();
  const [employeeData, setEmployeeData] = useState({
    firstName: "",
    lastName: "",
    dob: "",
    position: "",
    salary: 0,
  });

  useEffect(() => {
    const fetchEmployeeData = async () => {
      try {
        const response = await axios.get(
          `http://localhost:8082/employee-service/employees/getEmployee/${id}`
        );
        const fetchedEmployeeData = response.data;
        setEmployeeData(fetchedEmployeeData);
      } catch (error) {
        console.error("Error fetching employee data:", error);
      }
    };

    fetchEmployeeData();
  }, [employeeData.employeeId]);

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setEmployeeData({ ...employeeData, [name]: value });
  };

  const handleFormSubmit = async (e) => {
    e.preventDefault();

    try {
      const formData = new FormData();
      formData.append("firstName", employeeData.firstName);
      formData.append("lastName", employeeData.lastName);
      formData.append("dob", employeeData.dob);
      formData.append("position", employeeData.position);
      formData.append("salary", employeeData.salary);

      await axios.put(
        `http://localhost:8082/employee-service/employeesManage/update/${id}`,
        formData,
        {
          headers: {
            "Content-Type": "multipart/form-data",
          },
        }
      );

      console.log("Employee updated successfully");
    } catch (error) {
      console.error("Error updating employee:", error);
    }
  };

  const handleDelete = async () => {
    try {
      await axios.delete(
        `http://localhost:8082/employee-service/employeesManage/delete/${id}`
      );
      console.log("Employee deleted successfully");
      // You might want to redirect or handle the UI after deletion
    } catch (error) {
      console.error("Error deleting employee:", error);
    }
  };

  return (
    <div className="employee-form-container">

    <form className="employee-form" onSubmit={handleFormSubmit}>
    <label>
      ชื่อจริง:
      <input
        type="text"
        name="firstName"
        value={employeeData.firstName}
        onChange={handleInputChange}
        />
    </label>
    <br />
    <label>
      นามสกุล:
      <input
        type="text"
        name="lastName"
        value={employeeData.lastName}
        onChange={handleInputChange}
        />
    </label>
    <br />
    <label>
      วันเกิด:
      <input
        type="date"
        name="dob"
        value={employeeData.dob}
        onChange={handleInputChange}
        />
    </label>
    <br />
    <label>
      ตำแหน่ง:
      <input
        type="text"
        name="position"
        value={employeeData.position}
        onChange={handleInputChange}
        />
    </label>
    <br />
    <label>
      เงินเดือน:
      <input
        type="number"
        name="salary"
        value={employeeData.salary}
        onChange={handleInputChange}
        />
    </label>
    <br />
    <div className="buttons-container">
      <button type="submit">แก้ไข</button>
      <button class="delete"type="button" onClick={handleDelete}>
        ลบ
      </button>
    </div>
  </form>
        </div>
  );
};

export default UpdateEmployeeForm;