
import './App.css';
import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import EmployeeTable from './navigation/EmployeeTable';
import EmployeeDetails from './navigation/EmployeeDetails';
import UpdateEmployeeForm from './navigation/UpdateEmployeeForm';
import UpdateLeaveCountForm from './navigation/UpdateLeaveCountForm';
import UpdateLateCountForm from './navigation/UpdateLateCountForm';

function App() {
  return (
    <Router>
    <div className="App">
      <Routes>
  
        <Route path="/employee/:id" element={<EmployeeDetails />} />
        <Route path="/" element={<EmployeeTable />} />
        <Route path="/updateEmployee/:id" element={<UpdateEmployeeForm />} />
        <Route path="/updateLeaveCount/:id" element={<UpdateLeaveCountForm />} />
        <Route path="/updateLateCount/:id" element={<UpdateLateCountForm />} />
      </Routes>
    </div>
  </Router>
  );
}

export default App;
