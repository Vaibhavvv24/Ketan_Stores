/** @format */

// Navbar.js
import React, { useEffect } from "react";
import { Link, useLocation } from "react-router-dom";
import { useGlobalContext } from "../context";
import "./Navbar.css";

const Navbar = () => {
  const location = useLocation();

  useEffect(() => {
    window.scrollTo(0, 0); // Scroll to the top when the location changes
  }, [location]);

  return (
    <nav className='navbar navbar-expand-lg navbar-light bg-light mb-3'>
      <div className='container'>
        <Link className='navbar-brand' to='/ketan-stores'>
          Ketan Stores
        </Link>
        <div className='navbar-nav-center mx-5'>
          <ul className='navbar-nav'>
            <li className='nav-item'>
              <Link className='nav-link' to='/ketan-stores'>
                Home
              </Link>
            </li>
            <li className='nav-item'>
              <Link
                className='nav-link'
                to='/add-items'
              >
                Add Items
              </Link>
            </li>
            <li className='nav-item'>
              <Link className='nav-link' to='/'>
                Login
              </Link>
            </li>
            <li className='nav-item'>
              <Link
                  className='nav-link'
                  to='/ketan-stores/men'
                >
                  Men
                </Link>
            </li>
            <li className='nav-item'>
              <Link
                  className='nav-link'
                  to='/ketan-stores/men/kurta/silk'
                >
                  Kurta-Silk
                </Link>
            </li>
            <li className='nav-item'>
              <Link
                  className='nav-link'
                  to='/ketan-stores/men/kurta/cotton'
                >
                  Kurta-Cotton
                </Link>
            </li>
            <li className='nav-item'>
              <Link
                  className='nav-link'
                  to='/ketan-stores/men/Chudidar'
                >
                  Chudidar
                </Link>
            </li>
            <li className='nav-item'>
              <Link
                  className='nav-link'
                  to='/ketan-stores/men/jacket-suit'
                >
                  Jacket Suit
                </Link>
            </li>
            <li className='nav-item'>
              <Link
                  className='nav-link'
                  to='/ketan-stores/men/indo-western'
                >
                  Indo Western
                </Link>
            </li>
            <li className='nav-item'>
              <Link
                  className='nav-link'
                  to='/ketan-stores/men/short-kurta'
                >
                  Short Kurta
                </Link>
            </li>
            <li className='nav-item'>
              <Link
                  className='nav-link'
                  to='/ketan-stores/men/plus-size'
                >
                  Plus Size
                </Link>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  );
};

export default Navbar;
