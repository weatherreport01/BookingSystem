import { NavBar } from './components/NavBar.jsx';
import { Outlet } from 'react-router-dom';

function Layout(){
    return(
        <>
            <NavBar/>
            <main>
                <Outlet/>
            </main>
        </>
    )
}