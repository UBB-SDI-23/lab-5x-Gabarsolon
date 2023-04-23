import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import SmartphoneGetAll from './components/smartphone/SmartphoneGetAll'
import React from 'react'
import { AppMenu } from './components/AppMenu'
import { AppHome } from './components/AppHome'
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { SmartphoneAdd } from './components/smartphone/SmartphoneAdd'
import { SmartphoneDelete } from './components/smartphone/SmartphoneDelete'
import { SmartphoneUpdate } from './components/smartphone/SmartphoneUpdate'
import DisplayGetAll from './components/display/DisplayGetAll'
import { DisplayAdd } from './components/display/DisplayAdd'
import { DisplayDelete } from './components/display/DisplayDelete'
import { DisplayUpdate } from './components/display/DisplayUpdate'
import CustomerGetAll from './components/customer/CustomerGetAll'
import { CustomerAdd } from './components/customer/CustomerAdd'
import { CustomerDelete } from './components/customer/CustomerDelete'
import { CustomerUpdate } from './components/customer/CustomerUpdate'

function App() {
  const [count, setCount] = useState(0)

  return (
    <React.Fragment>
    <Router>
      <AppMenu/>
      <Routes>
        <Route path="/" element={<AppHome/>} />

        <Route path="/smartphones" element={<SmartphoneGetAll/>}/>
        <Route path="/smartphones/add" element={<SmartphoneAdd/>}/>
        <Route path="/smartphones/delete/:id" element={<SmartphoneDelete/>}/>
        <Route path="/smartphones/update/:id" element={<SmartphoneUpdate/>}/>

        <Route path="/displays" element={<DisplayGetAll/>}/>
        <Route path="/displays/add" element={<DisplayAdd/>}/>
        <Route path="/displays/delete/:id" element={<DisplayDelete/>}/>
        <Route path="/displays/update/:id" element={<DisplayUpdate/>}/>

        <Route path="/customers" element={<CustomerGetAll/>}/>
        <Route path="/customers/add" element={<CustomerAdd/>}/>
        <Route path="/customers/delete/:id" element={<CustomerDelete/>}/>
        <Route path="/customers/update/:id" element={<CustomerUpdate/>}/>
      </Routes>
    </Router>
  </React.Fragment>
  )
}

export default App