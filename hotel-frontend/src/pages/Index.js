import React, { useEffect } from 'react';
import { Link } from 'react-router-dom';
import '../assets/css/style-liberty.css';
import hotelLogo from '../assets/images/hotels.png';

// main slider images
import slide1 from '../assets/images/slide1.jpg';
import slide2 from '../assets/images/slide2.jpg';
import slide3 from '../assets/images/slide3.jpg';
import slide4 from '../assets/images/slide4.jpg';

//1st about images
import top from '../assets/images/top.jpg';
import bottom from '../assets/images/bottom.jpg';

const Index = () => {
    useEffect(() => {
        // Ensure jQuery and Owl Carousel are loaded
        const script = document.createElement('script');
        script.src = '/assets/js/owl.carousel.js';
        script.async = true;
        script.onload = () => {
            if (window.$) {
                window.$('.owl-carousel').owlCarousel({
                    items: 1, // Number of items to display at once
                    loop: true, // Enable looping
                    autoplay: true, // Enable autoplay
                    autoplayTimeout: 5000, // Autoplay interval
                    dots: true, // Enable dots
                });
            }
        };
        document.body.appendChild(script);

        return () => {
            document.body.removeChild(script);
        };
    }, []);

    return (
        <div>
            <header className="w3l-header-nav">
                <nav className="navbar navbar-expand-lg navbar-light fill px-lg-0 py-0 px-3">
                    <div className="container">
                        <Link className="navbar-brand" to="/">
                            <img src={hotelLogo} alt="Your logo" style={{height: '35px'}}/>
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

            {/*MAIN-SLIDER*/}
            <section className="w3l-main-slider" id="home">
                <div className="companies20-content">
                    <div className="owl-one owl-carousel owl-theme">
                        {/* Slider 1 */}
                        <div className="item">
                            <li>
                                <div
                                    className="slider-info banner-view bg bg2"
                                    style={{
                                        backgroundImage: `url(${slide1})`,
                                        backgroundSize: 'cover',
                                        backgroundPosition: 'center',
                                        minHeight: '700px',
                                    }}
                                >
                                    <div className="banner-info">
                                        <div className="container">
                                            <div className="banner-info-bg">
                                                <h5>Location is heavenly. Best to visit in week days to enjoy the
                                                    peaceful beauty</h5>
                                                <a
                                                    className="btn btn-style transparent-btn mt-sm-5 mt-4"
                                                    href="services.html"
                                                >
                                                    Our Services
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </li>
                        </div>

                        {/* Slider 2 */}
                        <div className="item">
                            <li>
                                <div
                                    className="slider-info banner-view bg bg2"
                                    style={{
                                        backgroundImage: `url(${slide2})`,
                                        backgroundSize: 'cover',
                                        backgroundPosition: 'center',
                                        minHeight: '700px',
                                    }}
                                >
                                    <div className="banner-info">
                                        <div className="container">
                                            <div className="banner-info-bg">
                                                <h5>Our new hotels will play a leading role in prompting the world
                                                    peace.</h5>
                                                <a
                                                    className="btn btn-style transparent-btn mt-sm-5 mt-4"
                                                    href="services.html"
                                                >
                                                    Our Services
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </li>
                        </div>

                        {/* Slider 3 */}
                        <div className="item">
                            <li>
                                <div
                                    className="slider-info banner-view bg bg2"
                                    style={{
                                        backgroundImage: `url(${slide3})`,
                                        backgroundSize: 'cover',
                                        backgroundPosition: 'center',
                                        minHeight: '700px',
                                    }}
                                >
                                    <div className="banner-info">
                                        <div className="container">
                                            <div className="banner-info-bg">
                                                <h5>Most hotels train their people with the booklets. We take ours to
                                                    the ballet.</h5>
                                                <a
                                                    className="btn btn-style transparent-btn mt-sm-5 mt-4"
                                                    href="services.html"
                                                >
                                                    Our Services
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </li>
                        </div>

                        {/* Slider 4 */}
                        <div className="item">
                            <li>
                                <div
                                    className="slider-info banner-view bg bg2"
                                    style={{
                                        backgroundImage: `url(${slide4})`,
                                        backgroundSize: 'cover',
                                        backgroundPosition: 'center',
                                        minHeight: '700px',
                                    }}
                                >
                                    <div className="banner-info">
                                        <div className="container">
                                            <div className="banner-info-bg">
                                                <h5>Good tourism will follow good hotels. Experience our luxury hotel
                                                    rooms</h5>
                                                <a
                                                    className="btn btn-style transparent-btn mt-sm-5 mt-4"
                                                    href="services.html"
                                                >
                                                    Our Services
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </li>
                        </div>
                    </div>
                </div>
            </section>

            {/*1st ABOUT*/}
            <section className="w3l-about py-5">
                <div className="container py-sm-4">
                    <div className="row">
                        <div className="col-lg-6 about-left mb-md-0 mb-5">
                            <h3 className="title-big">Relax in our Resort</h3>
                            <h5>We make the best for all our customers.</h5>
                            <p className="mt-3">
                                Duis nisi sapien, elementum finibus fermentum eget, aliquet leo. Mauris hendrerit vel
                                ex.
                                Quisque vitae luctus massa. Phasellus sed aliquam leo. Vestibulum ullamcorper a massa eu
                                fringilla. Integer ultrices finibus sed nisi. in convallis felis dapibus sit amet. Lorem
                                ipsum
                                dolor, sit amet consectetur adipisicing elit. Totam, porro! Lorem ipsum dolor sit amet.
                            </p>
                            <a href="about.html" className="btn btn-style btn-primary mt-sm-5 mt-4">
                                Learn About Us
                            </a>
                        </div>
                        <div className="col-lg-6 about-right position-relative mt-lg-0 mt-5">
                            <img src={top} alt="Top Image" className="img-fluid img-border mt-4"/>
                            <img src={bottom} alt="Bottom Image" className="img-fluid position-absolute img-position"
                            />
                        </div>
                    </div>
                </div>
            </section>


        </div>


    );
};

export default Index;
