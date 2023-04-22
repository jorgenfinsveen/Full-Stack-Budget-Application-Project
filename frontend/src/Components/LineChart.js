import React, { useState, useEffect } from 'react';
import { Line } from 'react-chartjs-2';
import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  Title,
  Tooltip,
  Legend,
} from 'chart.js';


ChartJS.register(
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  Title,
  Tooltip,
  Legend
);

export function LineChart(props) {

  const labels = [''];
  const [lines, setLines] = useState(
    {
      labels,
      datasets: [
        {
          label: 'Income',
          data: [0],
          borderColor: 'rgba(99, 255, 32, 1)',
          backgroundColor: 'rgba(99, 255, 32, 0.5)',
        },
        {
          label: 'Expense',
          data: [0],
          borderColor: 'rgba(255, 39, 32, 1)',
          backgroundColor: 'rgba(255, 39, 32, 0.5)',
        },
      ],
    }
  );
  

  const getData = (labels, incomes, expenses) => {
    return {
      labels,
      datasets: [
        {
          label: 'Income',
          data: incomes,
          borderColor: 'rgba(99, 255, 32, 1)',
          backgroundColor: 'rgba(99, 255, 32, 0.5)',
        },
        {
          label: 'Expense',
          data: expenses,
          borderColor: 'rgba(255, 39, 32, 1)',
          backgroundColor: 'rgba(255, 39, 32, 0.5)',
        },
      ],
    };
  };
  

  useEffect( () => {
    setLines(getData(props.labels, props.incomes, props.expenses));
  }, [props, props.labels, props.incomes, props.expenses]);


  const options = {
    responsive: true,
    interaction: {
      intersect: false,
      duration: 10,
      mode: 'index',
    },
    animation: {
      duration: 50
    },
    plugins: {
      legend: {
        position: 'top',
      },
      title: {
        display: false,
        text: 'Chart.js Line Chart',
      },
    },
  };


  return (
    <Line 
      options={options} 
      data={lines} 
      width={"80rem"}
      height={"20rem"}
    />
  );
}
