import React, { useState, useEffect } from "react";
import axios from "axios";
import { useParams } from 'react-router-dom';
import './UpdateForm.css';

const UpdateLateCountForm = () => {
    const { id } = useParams();
    const [lateCount, setLateCount] = useState(0);
  
    useEffect(() => {
      const fetchEmployeeData = async () => {
        try {
          const response = await axios.get(
            `http://localhost:8082/employee-service/employees/getEmployee/${id}`
          );
          const fetchedEmployeeData = response.data;
          setLateCount(fetchedEmployeeData.lateCount);
        } catch (error) {
          console.error("Error fetching employee data:", error);
        }
      };
  
      fetchEmployeeData();
    }, [id]);
  
    const handleLateCountChange = (e) => {
      const { value } = e.target;
      setLateCount(value);
    };
  
    const handleLateCountSubmit = async (e) => {
      e.preventDefault();
    
      try {
        const formData = new FormData();
        formData.append('lateCount', lateCount);
    
        await axios.put(
          `http://localhost:8082/employee-service/employeesManage/updateLate/${id}`,
          formData,
          {
            headers: {
              "Content-Type": "multipart/form-data",
            },
          }
        );
    
        console.log("Late count updated successfully");
      } catch (error) {
        console.error("Error updating late count:", error);
      }
    };
  
    return (
        <div className="employee-form-container">
      <div className="leave-count-form-container">
        <form className="leave-count-form" onSubmit={handleLateCountSubmit}>
          <label>
            จำนวนวันลา:
            <input
              type="number"
              name="lateCount"
              value={lateCount}
              onChange={handleLateCountChange}
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