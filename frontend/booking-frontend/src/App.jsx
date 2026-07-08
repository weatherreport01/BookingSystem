import { HashRouter as Router, Routes, Route} from 'react-router-dom';
import { Home } from './pages/home.jsx';
import { Booking } from './pages/booking.jsx';
import { Layout } from './Layout.jsx';
import { ProtectedRoutes } from './components/ProtectedRoutes.jsx';
import { Login } from './pages/login.jsx';
import { ManageBookings } from './pages/manageBookings.jsx';
import { ProtectedStaffRoute } from './components/ProtectedStaffRoute.jsx';
import { StaffMenu } from './pages/staffMenu.jsx';
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
            <Route path="/manage" element={<ManageBookings/>}/>
          </Route>
          <Route element={<ProtectedStaffRoute/>}>
            <Route path="/staff" element={<StaffMenu/>}/>
          </Route>
        </Route>
      </Routes>
    </Router>
  )
}

export default App
