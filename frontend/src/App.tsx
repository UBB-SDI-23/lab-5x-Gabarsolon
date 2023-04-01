import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import SmartphoneGetAll from './components/smartphone/SmartphoneGetAll'
import React from 'react'

function App() {
  const [count, setCount] = useState(0)

  return (
    <React.Fragment>
      <SmartphoneGetAll/>
    </React.Fragment> 
  )
}

export default App
