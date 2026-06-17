import axios from "axios";
import { useEffect, useState } from "react";
import BookingHistory from "../passenger-components/BookingHistory";
import BookingBarChart from "./BookingBarChart";

function WidgetsAdmin() {

  const statApi = "http://localhost:8080/api/admin/stat"
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
            <h6>{label.length > 0 ? label[0] : "Airlines"}</h6>
            <h2>{data.length > 0 ? data[0] : 0}</h2>
          </div>
        </div>

        <div className="col-lg-3 col-md-6">
          <div className="dashboard-card">
            <div className="card-icon">
              <i className="bi bi-airplane"></i>
            </div>
            <h6>{label.length > 1 ? label[1] : "Flights"}</h6>
            <h2>{data.length > 1 ? data[1] : 0}</h2>
          </div>
        </div>

        <div className="col-lg-3 col-md-6">
          <div className="dashboard-card">
            <div className="card-icon">
              <i className="bi bi-ticket-perforated"></i>
            </div>
            <h6>{label.length > 2 ? label[2] : "Bookings"}</h6>
            <h2>{data.length > 2 ? data[2] : 0}</h2>
          </div>
        </div>

        <div className="row justify-content-center align-items-stretch flex-grow-1 g-4 my-2">
          {/* <!-- Officers Data Box --> */}
          <div className="col-12 col-md-5 col-xl-4 d-flex">
            <div className="data-box flex-grow-1 border border-light bg-light bg-opacity-20 shadow-sm rounded-3 p-4 d-flex flex-column justify-content-between">
              <div className="d-flex align-items-center justify-content-between mb-3 border-bottom pb-2 border-light">
                <h4 className="fs-6 fw-bold text-dark mb-0">Incident Stats By Type</h4>
                <i className="bi bi-bar-chart-line text-muted"></i>
              </div>
              <div>
                <BookingBarChart/>
              </div>
            </div>
          </div>


        </div>



      </div>



    </>
  );
}

export default WidgetsAdmin;