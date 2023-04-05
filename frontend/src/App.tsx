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
      </Routes>
    </Router>
  </React.Fragment>
  )
}

export default App