import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useParams } from 'react-router-dom';
import { Link } from 'react-router-dom';
import './EmployeeDetails.css';
const EmployeeDetails = () => {
  const { id } = useParams();
  const [employee, setEmployee] = useState(null);
  const [selectedImage, setSelectedImage] = useState(null);

  const fetchEmployeeDetails = async () => {
    try {
      const response = await axios.get(`http://localhost:8082/employee-service/employees/getEmployee/${id}`);
      setEmployee(response.data[0]); // Assuming the response is an array with one employee
    } catch (error) {
      console.error('Error fetching employee details:', error);
    }
  };

  useEffect(() => {
    fetchEmployeeDetails();
  }, [id]);

  const handleImageChange = (event) => {
    setSelectedImage(event.target.files[0]);
  };

  const handleImageUpload = async () => {
    try {
      const formData = new FormData();
      formData.append('image', selectedImage);

      await axios.post(`http://localhost:8082/employee-service/employeesManage/imageAdd/${id}`, formData);

      // Assuming you want to refresh the employee details after uploading the image
      fetchEmployeeDetails();
    } catch (error) {
      console.error('Error uploading image:', error);
    }
  };

  const handleImageDelete = async () => {
    try {
      await axios.delete(`http://localhost:8082/employee-service/employeesManage/imageDelete/${employee.employeeId}`);

      // Assuming you want to refresh the employee details after deleting the image
      fetchEmployeeDetails();
    } catch (error) {
      console.error('Error deleting image:', error);
    }
  };
  if (!employee) {
    return <div>Loading...</div>;
  }

  return (
    <div className="employee-form-container">
<div className="employee-details-container">
  <div className="employee-image">
    <img
      src={`http://localhost:8082/employee-service/employees/getImage/${employee.employeeId}`}
      alt={`Image of ${employee.firstName} ${employee.lastName}`}
    />
    <input  type="file" accept="image/*" onChange={handleImageChange} />
    <button onClick={handleImageUpload}>อัพโหลด</button>
    <button class="delete"onClick={handleImageDelete}>ลบ</button>
  </div>
  <div className="employee-details">
    <h2>รายละเอียดพนักงาน</h2>
    <ul>
      <li>รหัสพนักงาน: {employee.employeeId}</li>
      <li>ชื่อจริง: {employee.firstName}</li>
      <li>นามสกุล: {employee.lastName}</li>
      <li>วันเกิด: {employee.dob}</li>
      <li>ตำแหน่ง: {employee.position}</li>
      <li>เงินเดือน: {employee.salary}</li>
      <li>วันลา: {employee.leaveCount}  <Link to={`/updateLeaveCount/${employee.employeeId}`}>แก้ไขจำนวนวันลา</Link></li>
      <li>วันสาย: {employee.lateCount}<Link to={`/updateLateCount/${employee.employeeId}`}>แก้ไขจำนวนวันสาย</Link> </li>
    </ul>
    <Link to={`/updateEmployee/${employee.employeeId}`}>แก้ไข</Link>
  </div>
</div>
</div>
  );
};

export default EmployeeDetails;