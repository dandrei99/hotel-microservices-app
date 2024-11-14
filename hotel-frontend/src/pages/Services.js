import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import axios from "axios";

import hotelLogo from '../assets/images/hotels.png';
import facilities from '../assets/images/facilities.jpg';


const Services = () => {
    const [services, setServices] = useState([]); // State to hold services

    // Fetch services from the API
    useEffect(() => {
        axios.get('http://localhost:9191/api/services')  //use API Gateway
            .then((response) => {
                console.log('Fetched services:', response.data);
                setServices(response.data); // Set fetched data
            })
            .catch((error) => {
                console.error('Error fetching services:', error);
            });
    }, []);

    return (
        <div>

            {/*Header*/}
            <header className="w3l-header-nav">
                <nav className="navbar navbar-expand-lg navbar-light fill px-lg-0 py-0 px-3">
                    <div className="container">
                        <Link className="navbar-brand" to="/">
                            <img src={hotelLogo} alt="Your logo" style={{height: '35px'}}/> Hotels
                        </Link>
                        <button className="navbar-toggler collapsed" type="button" data-toggle="collapse"
                                data-target="#navbarSupportedContent"
                                aria-controls="navbarSupportedContent" aria-expanded="false"
                                aria-label="Toggle navigation">
                            <span className="fa icon-expand fa-bars"></span>
                            <span className="fa icon-close fa-times"></span>
                        </button>

                        <div className="collapse navbar-collapse" id="navbarSupportedContent">
                            <ul className="navbar-nav ml-auto">
                                <li className="nav-item @@home__active">
                                    <Link className="nav-link" to="/">Home</Link>
                                </li>
                                <li className="nav-item @@about__active">
                                    <a className="nav-link" href="about.html">About</a>
                                </li>
                                <li className="nav-item active">
                                    <Link className="nav-link" to="/services">Services</Link>
                                </li>
                                <li className="nav-item dropdown @@room__active">
                                    <a className="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                                       data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        Rooms <span className="fa fa-angle-down"></span>
                                    </a>
                                    <div className="dropdown-menu" aria-labelledby="navbarDropdown">
                                        <Link className="dropdown-item" to="/rooms">Rooms</Link>
                                        <a className="dropdown-item" href="room-single.html">Room Single</a>
                                    </div>
                                </li>
                                <li className="nav-item dropdown @@blog__active">
                                    <a className="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                                       data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        Blog <span className="fa fa-angle-down"></span>
                                    </a>
                                    <div className="dropdown-menu" aria-labelledby="navbarDropdown">
                                        <a className="dropdown-item" href="blog.html">Blog</a>
                                        <a className="dropdown-item" href="blog-single.html">Blog Single</a>
                                    </div>
                                </li>
                                <li className="nav-item @@contact__active">
                                    <a className="nav-link" href="contact.html">Contact</a>
                                </li>
                            </ul>
                            <a href="booking.html" className="ml-3 book btn btn-secondary btn-style">Book Now</a>
                        </div>
                    </div>
                </nav>
            </header>

            {/*Breadcrumb*/}
            <section className="w3l-breadcrumb">
                <div className="breadcrum-bg py-sm-5 py-4">
                    <div className="container py-lg-3">

                        <h2>Our Services</h2>
                        <p><a href="index.html">Home</a> &nbsp; / &nbsp; Services</p>

                    </div>
                </div>
            </section>

            {/*Services Section*/}
            <section className="w3l-servicesblock1" id="service">
                <div className="features-with-17_sur py-5">
                    <div className="container py-lg-5 py-sm-4">
                        <div className="features-with-17-top_sur">
                            <div className="row">
                                <div className="col-lg-4 col-md-6 align-self">
                                    <h3 className="title-small">Luxury Hotel Services</h3>
                                    <p>Our rooms are beautifully designed and comfortable and can accommodate various
                                        groups of
                                        travelers. All rooms have private bathrooms, a flat-screen TV and a safe.</p>
                                </div>

                                    {services.map((service) => (
                                        <div className="col-lg-4 col-md-6 mt-4" key={service.service_id}>
                                            <div className="features-with-17-right-tp_sur">
                                                <div className="features-with-17-left1">
                                                    {/*<span className="fa fa-star" aria-hidden="true"></span>*/}
                                                    <h4>{service.serviceName}</h4>
                                                    <br/>
                                                </div>
                                                <div className="features-with-17-left2">
                                                    {/*<h5>{service.serviceName}</h5>*/}
                                                    <h6>${service.price}</h6>
                                                    <p>{service.description}</p>
                                                </div>
                                            </div>
                                        </div>
                                    ))}
                                {/*<div className="col-lg-4 col-md-6 mt-md-0 mt-4">*/}
                                {/*    <div className="features-with-17-right-tp_sur">*/}
                                {/*        <div className="features-with-17-left1">*/}
                                {/*            <span className="fa fa-bed" aria-hidden="true"></span>*/}
                                {/*        </div>*/}
                                {/*        <div className="features-with-17-left2">*/}
                                {/*            <h5>Fitness Zone</h5>*/}
                                {/*            <h6><a href="#url">Swimming Pool</a></h6>*/}
                                {/*            <p>Lorem ipsum dolor sit amet elit. Id, minima. It is a long fact that a*/}
                                {/*                reader will be*/}
                                {/*                distracted by the readable.</p>*/}
                                {/*        </div>*/}
                                {/*    </div>*/}
                                {/*</div>*/}
                                {/*<div className="col-lg-4 col-md-6 mt-lg-0 mt-sm-5 mt-4">*/}
                                {/*    <div className="features-with-17-right-tp_sur">*/}
                                {/*        <div className="features-with-17-left1">*/}
                                {/*            <span className="fa fa-beer" aria-hidden="true"></span>*/}
                                {/*        </div>*/}
                                {/*        <div className="features-with-17-left2">*/}
                                {/*            <h5>Food & Drinks</h5>*/}
                                {/*            <h6><a href="#url">Restaurant and Bar</a></h6>*/}
                                {/*            <p>Lorem ipsum dolor sit amet elit. Id, minima. It is a long fact that a*/}
                                {/*                reader will be*/}
                                {/*                distracted by the readable.</p>*/}
                                {/*        </div>*/}
                                {/*    </div>*/}
                                {/*</div>*/}
                                {/*<div className="col-lg-4 col-md-6 mt-sm-5 mt-4">*/}
                                {/*    <div className="features-with-17-right-tp_sur">*/}
                                {/*        <div className="features-with-17-left1">*/}
                                {/*            <span className="fa fa-wifi" aria-hidden="true"></span>*/}
                                {/*        </div>*/}
                                {/*        <div className="features-with-17-left2">*/}
                                {/*            <h5>Accommodation</h5>*/}
                                {/*            <h6><a href="#url">High speed WiFi</a></h6>*/}
                                {/*            <p>Lorem ipsum dolor sit amet elit. Id, minima. It is a long fact that a*/}
                                {/*                reader will be*/}
                                {/*                distracted by the readable.</p>*/}
                                {/*        </div>*/}
                                {/*    </div>*/}
                                {/*</div>*/}
                                {/*<div className="col-lg-4 col-md-6 mt-sm-5 mt-4">*/}
                                {/*    <div className="features-with-17-right-tp_sur">*/}
                                {/*        <div className="features-with-17-left1">*/}
                                {/*            <span className="fa fa-bed" aria-hidden="true"></span>*/}
                                {/*        </div>*/}
                                {/*        <div className="features-with-17-left2">*/}
                                {/*            <h5>Comfort & Relax</h5>*/}
                                {/*            <h6><a href="#url">SPA & Wellness</a></h6>*/}
                                {/*            <p>Lorem ipsum dolor sit amet elit. Id, minima. It is a long fact that a*/}
                                {/*                reader will be*/}
                                {/*                distracted by the readable.</p>*/}
                                {/*        </div>*/}
                                {/*    </div>*/}
                                {/*</div>*/}
                                {/*<div className="col-lg-4 col-md-6 mt-sm-5 mt-4">*/}
                                {/*    <div className="features-with-17-right-tp_sur">*/}
                                {/*        <div className="features-with-17-left1">*/}
                                {/*            <span className="fa fa-headphones" aria-hidden="true"></span>*/}
                                {/*        </div>*/}
                                {/*        <div className="features-with-17-left2">*/}
                                {/*            <h5>Safe & Secure </h5>*/}
                                {/*            <h6><a href="#url">Staff 24/7</a></h6>*/}
                                {/*            <p>Lorem ipsum dolor sit amet elit. Id, minima. It is a long fact that a*/}
                                {/*                reader will be*/}
                                {/*                distracted by the readable.</p>*/}
                                {/*        </div>*/}
                                {/*    </div>*/}
                                {/*</div>*/}
                            </div>
                        </div>
                    </div>
                </div>
            </section>

            {/*Facilities section*/}
            <section className="w3l-stats">
                <div className="main-w3 py-5">
                    <div className="container py-lg-3">
                        <div className="row align-items-center">
                            <div className="col-md-6 title">
                                <h3 className="title-big">Our Hotel Facilities</h3>
                                <img src={facilities} alt="Facilities" className="img-fluid"/>
                            </div>
                            <div className="col-md-6 main-cont-wthree-fea text-center">
                                <div className="row">
                                    <div className="col-6 pr-2 mt-4">
                                        <div className="grids-speci1">
                                            <span className="fa fa-television"></span>
                                            <h3 className="title-spe">Tv</h3>
                                            <p> Satellite</p>
                                        </div>
                                    </div>
                                    <div className="col-6 pl-2 mt-4">
                                        <div className="grids-speci1">
                                            <span className="fa fa-motorcycle"></span>
                                            <h3 className="title-spe">Bike</h3>
                                            <p>Rental</p>
                                        </div>
                                    </div>
                                    <div className="col-6 pr-2 mt-4">
                                        <div className="grids-speci1">
                                            <span className="fa fa-cutlery"></span>
                                            <h3 className="title-spe">Food</h3>
                                            <p>Included</p>
                                        </div>
                                    </div>
                                    <div className="col-6 pl-2 mt-4">
                                        <div className="grids-speci1">
                                            <span className="fa fa-bed"></span>
                                            <h3 className="title-spe">Bed</h3>
                                            <p>King size</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </section>

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

export default Services;
