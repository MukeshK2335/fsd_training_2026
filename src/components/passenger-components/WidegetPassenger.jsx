import axios from "axios";
import { useEffect, useState } from "react";
import BookingStatusBarChart from "./BookingStatusBarChart";
import BookingPassengerBarChart from "./BookingPassengerBarChart";

function WidgetsPasseneger() {

    const statApi = "http://localhost:8080/api/passenger/stat"
    const [label, setLabel] = useState([])
    const [data, setData] = useState([])

    useEffect(() => {
        const config_details = {
            headers: {
                'Authorization': "Bearer " + localStorage.getItem('token')
            }
        }
        const getStat = async () => {
            try {
                const resp = await axios.get(statApi, config_details)
                setLabel(resp.data.label)
                setData(resp.data.count)
            }
            catch (err) {
                console.log(err)
            }
        }
        getStat()
    }, [])
    return (
        <>
            <div className="row g-4">

                <div className="col-lg-3 col-md-6">
                    <div className="dashboard-card">
                        <div className="card-icon">
                            <i className="bi bi-people"></i>
                        </div>
                        <h6>{label.length > 0 ? label[0] : "Bookings"}</h6>
                        <h2>{data.length > 0 ? data[0] : 0}</h2>
                    </div>
                </div>

                {/* <div className="col-lg-3 col-md-6">
          <div className="dashboard-card">
            <div className="card-icon">
              <i className="bi bi-airplane"></i>
            </div>
            <h6>{label.length > 1? label[1]: "Flights" }</h6>
            <h2>{data.length > 1? data[1]: 0 }</h2>
          </div>
        </div>

        <div className="col-lg-3 col-md-6">
          <div className="dashboard-card">
            <div className="card-icon">
              <i className="bi bi-ticket-perforated"></i>
            </div>
            <h6>{label.length > 2? label[2]: "Bookings" }</h6>
            <h2>{data.length > 2? data[2]: 0 }</h2>
          </div>
        </div> */}
        <div className="row g-4 mt-3">
                <div className="col-12 col-lg-6 d-flex">
                    <div className="data-box flex-grow-1 border bg-light shadow-sm rounded-3 p-4 d-flex flex-column">

                        <div className="d-flex justify-content-between align-items-center border-bottom pb-2 mb-3">
                            <h5 className="fw-bold mb-0">Booking Status</h5>
                            <i className="bi bi-bar-chart-line"></i>
                        </div>

                        <div className="flex-grow-1">
                            <BookingStatusBarChart/>
                        </div>

                    </div>
                </div>

                {/* RIGHT CHART */}
                <div className="col-12 col-lg-6 d-flex">
                    <div className="data-box flex-grow-1 border bg-light shadow-sm rounded-3 p-4 d-flex flex-column">

                        <div className="d-flex justify-content-between align-items-center border-bottom pb-2 mb-3">
                            <h5 className="fw-bold mb-0">Passenger Analytics</h5>
                            <i className="bi bi-bar-chart"></i>
                        </div>

                        <div className="flex-grow-1">
                            <BookingPassengerBarChart label={label} data={data} />
                        </div>

                    </div>
                </div>





            </div>
            </div>




        </>
    );
}


export default WidgetsPasseneger