import React, { useState } from 'react';
import axios from 'axios';
const AddEmployeeForm = ({onAddEmployee}) => {
    const [formData, setFormData] = useState({
      firstName: '',
      lastName: '',
      dob: '',
      position: '',
      salary: '',
    });
  
    const [loading, setLoading] = useState(false);
  
    const handleChange = (e) => {
      const { name, value } = e.target;
      setFormData((prevData) => ({
        ...prevData,
        [name]: value,
      }));
    };
  
    const handleSubmit = async (e) => {
      e.preventDefault();
  
      // Basic form validation
      if (!formData.firstName || !formData.lastName || !formData.dob || !formData.position || !formData.salary) {
        alert('Please fill in all required fields.');
        return;
      }
  
      setLoading(true);
  
      try {
        const formDataObject = new FormData();
        formDataObject.append('firstName', formData.firstName);
        formDataObject.append('lastName', formData.lastName);
        formDataObject.append('dob', formData.dob);
        formDataObject.append('position', formData.position);
        formDataObject.append('salary', formData.salary);
  
        const response = await axios.post(
          'http://localhost:8082/employee-service/employeesManage/createEmployee',
          formDataObject
        );
        onAddEmployee(response.data);
  
        // Display the response in an alert
        alert(`Employee added successfully: ${JSON.stringify(response.data)}`);
  
        // Clear the form after successful submission
        setFormData({
          firstName: '',
          lastName: '',
          dob: '',
          position: '',
          salary: '',
        });
      } catch (error) {
        console.error('Error adding employee:', error);
  
        // Display the error message in an alert
        alert(`Error adding employee: ${error.message}`);
      } finally {
        setLoading(false);
      }
    };
  
    return (
      <div>
        <h2>เพิ่มข้อมูลพนักงาน</h2>
        <form onSubmit={handleSubmit}>
            <label>
              ชื่อจริง:
              <input type="text" name="firstName" value={formData.firstName} onChange={handleChange} />
            </label>
            <br />
            <label>
              นามสกุล:
              <input type="text" name="lastName" value={formData.lastName} onChange={handleChange} />
            </label>
            <br />
            <label>
              วันเกิด:
              <input type="date" name="dob" value={formData.dob} onChange={handleChange} />
            </label>
            <br />
            <label>
              ตำแหน่ง:
              <input type="text" name="position" value={formData.position} onChange={handleChange} />
            </label>
            <br />
            <label>
              เงินเดือน:
              <input type="number" name="salary" value={formData.salary} onChange={handleChange} />
            </label>
            <br />
            <button type="submit" disabled={loading}>
          {loading ? 'Adding Employee...' : 'Add Employee'}
        </button>
      </form>
    </div>
  );
};

export default AddEmployeeForm;