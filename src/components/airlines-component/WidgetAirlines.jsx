import axios from "axios";
import { useEffect, useState } from "react";

function WidgetsAirlines() {

  const statApi = "http://localhost:8080/api/flight-owner/stat"
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
            <h6>{label.length > 0? label[0]: "Schedules" }</h6>
            <h2>{data.length > 0? data[0]: 0 }</h2>
          </div>
        </div>

        <div className="col-lg-3 col-md-6">
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
        </div>

        {/* <div className="col-lg-3 col-md-6">
          <div className="dashboard-card">
            <div className="card-icon">
              <i className="bi bi-currency-rupee"></i>
            </div>
            <h6>Revenue</h6>
            <h2>₹8.4L</h2>
            <p>This Month</p>
          </div>
        </div> */}

      </div>

      

      
    </>
  );
}

export default WidgetsAirlines;