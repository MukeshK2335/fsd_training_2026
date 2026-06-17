import axios from "axios";
import { useEffect, useState } from "react";
import { Chart } from 'primereact/chart';

function BookingStatusBarChart() {

    const BarApi = "http://localhost:8080/api/passenger/stat/booking-status"

    const [chartData, setChartData] = useState({});
    const [chartOptions, setChartOptions] = useState({});

    useEffect(() => {
        const config_details = {
            headers: {
                'Authorization': "Bearer " + localStorage.getItem('token')
            }
        }

        const getIncidentBar =async () => {

            try {
                const response = await axios.get(BarApi, config_details)

                const data = {
                    labels: response.data.labels,
                    datasets: [
                        {
                            label: response.data.title,
                            data: response.data.data,
                            backgroundColor: [
                                'rgba(255, 159, 64, 0.2)',
                                'rgba(75, 192, 192, 0.2)',
                                'rgba(54, 162, 235, 0.2)',
                                'rgba(153, 102, 255, 0.2)'
                            ],
                            borderColor: [
                                'rgb(255, 159, 64)',
                                'rgb(75, 192, 192)',
                                'rgb(54, 162, 235)',
                                'rgb(153, 102, 255)'
                            ],
                            borderWidth: 1
                        }
                    ]
                };
                const options = {
                    scales: {
                        y: {
                            beginAtZero: true
                        }
                    }
                };

                setChartData(data);
                setChartOptions(options);

            }
            catch (err) {
                console.log(err)
            }
        }

        getIncidentBar()


    }, [])
    return (
        
        <div>
            <div>
                <Chart type="bar" data={chartData} options={chartOptions} />
            </div>
            
        </div>
    )
}
export default BookingStatusBarChart