import React, { useEffect } from 'react';
import { Link } from 'react-router-dom';
import '../assets/css/style-liberty.css';
import hotelLogo from '../assets/images/hotels.png';

const Index = () => {
    useEffect(() => {
        const scripts = [
            '/assets/js/jquery-3.3.1.min.js',
            '/assets/js/bootstrap.min.js',
            '/assets/js/jquery.magnific-popup.min.js',
            '/assets/js/owl.carousel.js',
        ];

        scripts.forEach((src) => {
            const script = document.createElement('script');
            script.src = src;
            script.async = true;
            document.body.appendChild(script);

            return () => {
                document.body.removeChild(script);
            };
        });
    }, []);

    return (
        <div>
            <header className="w3l-header-nav">
                <nav className="navbar navbar-expand-lg navbar-light fill px-lg-0 py-0 px-3">
                    <div className="container">
                        <Link className="navbar-brand" to="/">
                            <img src={hotelLogo} alt="Your logo" style={{ height: '35px' }} />
                            Hotels
                        </Link>
                        <button
                            className="navbar-toggler collapsed"
                            type="button"
                            data-toggle="collapse"
                            data-target="#navbarSupportedContent"
                            aria-controls="navbarSupportedContent"
                            aria-expanded="false"
                            aria-label="Toggle navigation"
                        >
                            <span className="fa icon-expand fa-bars"></span>
                            <span className="fa icon-close fa-times"></span>
                        </button>

                        <div className="collapse navbar-collapse" id="navbarSupportedContent">
                            <ul className="navbar-nav ml-auto">
                                <li className="nav-item active">
                                    <Link className="nav-link" to="/">
                                        Home
                                    </Link>
                                </li>
                                <li className="nav-item">
                                    <Link className="nav-link" to="/about">
                                        About
                                    </Link>
                                </li>
                                <li className="nav-item">
                                    <Link className="nav-link" to="/services">
                                        Services
                                    </Link>
                                </li>
                                <li className="nav-item dropdown">
                                    <button
                                        className="nav-link dropdown-toggle btn btn-link"
                                        id="navbarDropdown"
                                        role="button"
                                        data-toggle="dropdown"
                                        aria-haspopup="true"
                                        aria-expanded="false"
                                    >
                                        Rooms <span className="fa fa-angle-down"></span>
                                    </button>
                                    <div className="dropdown-menu" aria-labelledby="navbarDropdown">
                                        <Link className="dropdown-item" to="/room">
                                            Rooms
                                        </Link>
                                        <Link className="dropdown-item" to="/room-single">
                                            Room Single
                                        </Link>
                                    </div>
                                </li>
                                <li className="nav-item dropdown">
                                    <button
                                        className="nav-link dropdown-toggle btn btn-link"
                                        id="navbarDropdown"
                                        role="button"
                                        data-toggle="dropdown"
                                        aria-haspopup="true"
                                        aria-expanded="false"
                                    >
                                        Blog <span className="fa fa-angle-down"></span>
                                    </button>
                                    <div className="dropdown-menu" aria-labelledby="navbarDropdown">
                                        <Link className="dropdown-item" to="/blog">
                                            Blog
                                        </Link>
                                        <Link className="dropdown-item" to="/blog-single">
                                            Blog Single
                                        </Link>
                                    </div>
                                </li>
                                <li className="nav-item">
                                    <Link className="nav-link" to="/contact">
                                        Contact
                                    </Link>
                                </li>
                            </ul>
                            <Link to="/booking" className="ml-3 book btn btn-secondary btn-style">
                                Book Now
                            </Link>
                        </div>
                    </div>
                </nav>
            </header>
        </div>
    );
};

export default Index;
