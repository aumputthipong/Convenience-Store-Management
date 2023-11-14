import React, { useState, useEffect } from "react";
import axios from "axios";
import { useParams } from 'react-router-dom';
import './UpdateForm.css';

const UpdateLateCountForm = () => {
  const { id } = useParams();
  const [leaveCount, setLeaveCount] = useState(0);

  useEffect(() => {
    const fetchEmployeeData = async () => {
      try {
        const response = await axios.get(
          `http://localhost:8082/employee-service/employees/getEmployee/${id}`
        );
        const fetchedEmployeeData = response.data;
        setLeaveCount(fetchedEmployeeData.leaveCount);
      } catch (error) {
        console.error("Error fetching employee data:", error);
      }
    };

    fetchEmployeeData();
  }, [id]);

  const handleLeaveCountChange = (e) => {
    const { value } = e.target;
    setLeaveCount(value);
  };

  const handleLeaveCountSubmit = async (e) => {
    e.preventDefault();

    try {
      const formData = new FormData();
      formData.append('leaveCount', leaveCount);

      await axios.put(
        `http://localhost:8082/employee-service/employeesManage/updateLeave/${id}`,
        formData,
        {
          headers: {
            "Content-Type": "multipart/form-data",
          },
        }
      );

      console.log("Leave count updated successfully");
    } catch (error) {
      console.error("Error updating leave count:", error);
    }
  };
  return (
    <div className="employee-form-container">
    <div className="leave-count-form-container">
    <form className="leave-count-form" onSubmit={handleLeaveCountSubmit}>
      <label>
        จำนวนวันลา:
        <input
          type="number"
          name="leaveCount"
          value={leaveCount}
          onChange={handleLeaveCountChange}
        />
      </label>
      <br />
      <button type="submit">แก้ไขจำนวนวันลา</button>
    </form>
  </div>
  </div>
);
};

export default UpdateLateCountForm;