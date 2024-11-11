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

//2nd about images
import room1 from '../assets/images/room1.jpg';
import room2 from '../assets/images/room2.jpg';
import room3 from '../assets/images/room3.jpg';
import room4 from '../assets/images/room4.jpg';
import room5 from '../assets/images/room5.jpg';

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
                                                <Link className="btn btn-style transparent-btn mt-sm-5 mt-4"
                                                      to="/services">Our Services</Link>
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
                                                <Link className="btn btn-style transparent-btn mt-sm-5 mt-4"
                                                      to="/services">Our Services</Link>
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
                                                <Link className="btn btn-style transparent-btn mt-sm-5 mt-4"
                                                      to="/services">Our Services</Link>
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
                                                <Link className="btn btn-style transparent-btn mt-sm-5 mt-4"
                                                      to="/services">Our Services</Link>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </li>
                        </div>
                    </div>
                </div>
            </section>

            {/*1st ABOUT section*/}
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

            {/*2nd ABOUT section*/}
            <div className="best-rooms py-5">
                <div className="container py-lg-5 py-sm-4">
                    <h3 className="title-big text-center">Best Rooms</h3>
                    <div className="ban-content-inf row py-lg-3">
                        <div className="maghny-gd-1 col-lg-6">
                            <div className="maghny-grid">
                                <figure className="effect-lily">
                                    <img src={room1} className="img-fluid" alt="Room1"/>
                                    <figcaption className="w3set-hny">
                                        <div>
                                            <h4 className="top-text">Luxury Hotel and Best Resort
                                                <span>Peaceful Place to stay</span></h4>
                                            <p>From 20$ </p>
                                        </div>
                                    </figcaption>
                                </figure>
                                <div className="room-info">
                                    <h3 className="room-title"><a href="room-single.html">Luxury Hotel</a></h3>
                                    <ul className="mb-3">
                                        <li><span className="fa fa-users"></span> 2 Guests</li>
                                        <li><span className="fa fa-bed"></span> Double bed</li>
                                        <li><span className="fa fa-bed"></span> 15sqft</li>
                                    </ul>
                                    <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. A recusandae, illum
                                        sequi numquam
                                        tempora voluptates?</p>
                                    <a href="#book" className="btn btn-style btn-primary mt-sm-4 mt-3">Book Now</a>
                                </div>
                            </div>
                        </div>
                        <div className="maghny-gd-1 col-lg-6 mt-lg-0 mt-4">
                            <div className="row">
                                <div className="maghny-gd-1 col-6">
                                    <div className="maghny-grid">
                                        <figure className="effect-lily border-radius">
                                            <img src={room2} className="img-fluid" alt="Room2"/>
                                            <figcaption>
                                                <div>
                                                    <h4>Family Rooms <span> Resort</span></h4>
                                                    <p>From 20$ </p>
                                                </div>

                                            </figcaption>
                                        </figure>
                                    </div>
                                </div>
                                <div className="maghny-gd-1 col-6">
                                    <div className="maghny-grid">
                                        <figure className="effect-lily border-radius">
                                            <img src={room3} className="img-fluid" alt="Room3"/>
                                            <figcaption>
                                                <div>
                                                    <h4>Double Rooms <span> Resort</span></h4>
                                                    <p>From 20$ </p>
                                                </div>

                                            </figcaption>
                                        </figure>
                                    </div>
                                </div>
                                <div className="maghny-gd-1 col-6 mt-4">
                                    <div className="maghny-grid">
                                        <figure className="effect-lily border-radius">
                                            <img src={room4} className="img-fluid" alt="Room4"/>
                                            <figcaption>
                                                <div>
                                                    <h4>Luxury Rooms <span> Resort</span></h4>
                                                    <p>From 20$ </p>
                                                </div>

                                            </figcaption>
                                        </figure>
                                    </div>
                                </div>
                                <div className="maghny-gd-1 col-6 mt-4">
                                    <div className="maghny-grid">
                                        <figure className="effect-lily border-radius">
                                            <img src={room5} className="img-fluid" alt="Room5"/>
                                            <figcaption>
                                                <div>
                                                    <h4>Resort Rooms <span> Resort</span></h4>
                                                    <p>From 20$ </p>
                                                </div>

                                            </figcaption>
                                        </figure>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            {/*Footer*/}
            <section className="w3l-footer-29-main">
                <div className="footer-29 py-5">
                    <div className="container py-lg-4">
                        <div className="row footer-top-29">
                            <div className="col-lg-3 col-md-6 col-sm-8 footer-list-29 footer-1">
                                <h6 className="footer-title-29">Contact Us</h6>
                                <ul>
                                    <li>
                                        <p><span className="fa fa-map-marker"></span> Luxury hotel, #32841 block,
                                            #221DRS Rental & Paid rooms
                                            business, UK.</p>
                                    </li>
                                    <li><a href="tel:+7-800-999-800"><span
                                        className="fa fa-phone"></span> +(21)-255-999-8888</a></li>
                                    <li><a href="mailto:hotels@mail.com" className="mail"><span
                                        className="fa fa-envelope-open-o"></span>
                                        hotels@mail.com</a></li>
                                </ul>
                            </div>
                            <div className="col-lg-2 col-md-6 col-sm-4 footer-list-29 footer-2 mt-sm-0 mt-5">

                                <ul>
                                    <h6 className="footer-title-29">Useful Links</h6>
                                    <li><a href="index.html">Home</a></li>
                                    <li><a href="about.html">About hotels</a></li>
                                    <li><a href="blog.html"> Blog posts</a></li>
                                    <li><a href="contact.html">Contact us</a></li>
                                </ul>
                            </div>
                            <div className="col-lg-3 col-md-6 col-sm-5 footer-list-29 footer-3 mt-lg-0 mt-5">
                                <h6 className="footer-title-29">Latest from blog</h6>
                                <div className="footer-post mb-4">
                                    <a href="blog-single.html">Work Passionately</a>
                                    <p className="small"><span className="fa fa-clock-o"></span> March 9, 2020</p>
                                </div>
                                <div className="footer-post">
                                    <a href="blog-single.html">Work Passionately without any hesitation</a>
                                    <p className="small"><span className="fa fa-clock-o"></span> March 9, 2020</p>
                                </div>

                            </div>
                            <div className="col-lg-4 col-md-6 col-sm-7 footer-list-29 footer-4 mt-lg-0 mt-5">
                                <h6 className="footer-title-29">Newsletter </h6>
                                <p>Enter your email and receive the latest news from us.
                                    We'll never share your email address</p>

                                <form action="#" className="subscribe" method="post">
                                    <input type="email" name="email" placeholder="Your Email Address" required=""/>
                                    <button><span className="fa fa-envelope-o"></span></button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </section>


        </div>


    );
};

export default Index;
