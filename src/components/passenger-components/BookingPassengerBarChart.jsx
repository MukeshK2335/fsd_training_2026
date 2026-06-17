import { Chart } from "primereact/chart";
import { useEffect, useState } from "react";

function BookingPassengerBarChart({ label, data }) {

    const [chartData, setChartData] = useState({});
    const [chartOptions, setChartOptions] = useState({});

    useEffect(() => {

        const chart = {
            labels: label,
            datasets: [
                {
                    label: "Bookings",
                    data: data,
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
            maintainAspectRatio: false,
            responsive: true,
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        };

        setChartData(chart);
        setChartOptions(options);

    }, [label, data]); // IMPORTANT

    return (
        <div style={{ height: "450px", width: "100%" }}>
            <Chart type="bar" data={chartData} options={chartOptions} />
        </div>
    );
}

export default BookingPassengerBarChart;