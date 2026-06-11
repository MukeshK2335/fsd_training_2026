import axios from "axios";
import { useEffect, useState } from "react";

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
            <h6>{label.length > 0? label[0]: "Airlines" }</h6>
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

      <div className="table-card mt-4">

        <h4 className="mb-4">Recent Bookings</h4>

        <div className="table-responsive">

          <table className="table align-middle">

            <thead>
              <tr>
                <th>Passenger</th>
                <th>Flight</th>
                <th>Route</th>
                <th>Status</th>
              </tr>
            </thead>

            <tbody>

              <tr>
                <td>Mukesh K</td>
                <td>6E-305</td>
                <td>DEL → BOM</td>
                <td>
                  <span className="status success">
                    Confirmed
                  </span>
                </td>
              </tr>

              <tr>
                <td>Priya N</td>
                <td>6E-201</td>
                <td>HYD → DEL</td>
                <td>
                  <span className="status success">
                    Confirmed
                  </span>
                </td>
              </tr>

              <tr>
                <td>Arjun R</td>
                <td>AI-101</td>
                <td>BOM → CHN</td>
                <td>
                  <span className="status pending">
                    Pending
                  </span>
                </td>
              </tr>

            </tbody>

          </table>

        </div>

      </div>
    </>
  );
}

export default WidgetsAdmin;