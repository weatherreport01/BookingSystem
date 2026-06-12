import { HashRouter as Router, Routes, Route} from 'react-router-dom';
import { Home } from './pages/home.jsx';
import { Booking } from './pages/booking.jsx';
import { Layout } from './Layout.jsx';
import { ProtectedRoutes } from './components/ProtectedRoutes.jsx';
import { Login } from './pages/login.jsx';
// add more pages later
// use <Route path="/(WHATEVER)" element={<THE PAGE HERE/>}

function App() {

  return (
    <Router>
      <Routes>
        <Route element={<Layout/>}>
          <Route path="/" element={<Home/>}/>
          <Route path="/login" element={<Login/>}/>
          <Route element={<ProtectedRoutes/>}>
            <Route path="/booking" element={<Booking/>}/>
          </Route>
        </Route>
      </Routes>
    </Router>
  )
}

export default App
