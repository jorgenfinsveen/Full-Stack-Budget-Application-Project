import React, { useState, useEffect } from 'react';
import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  BarElement,
  Title,
  Tooltip,
  Legend,
} from 'chart.js';
import { Bar } from 'react-chartjs-2';

ChartJS.register(
  CategoryScale,
  LinearScale,
  BarElement,
  Title,
  Tooltip,
  Legend
);

export function BarChart(props) {

  const labels = [''];
  const [bar, setBar] = useState(
    {
      labels,
      datasets: [
        {
          label: 'Income',
          data: labels.map(() => 0),
          backgroundColor: 'rgba(99, 255, 32, 0.5)',
        },
        {
          label: 'Expenses',
          data: labels.map(() => 0),
          backgroundColor: 'rgba(255, 39, 32, 0.5)',
        },
      ]
    }
  );

  const getData = (incomes, expenses) => {
    return {
      labels,
      datasets: [
        {
          label: 'Income',
          data: labels.map(() => incomes),
          backgroundColor: 'rgba(99, 255, 32, 0.5)',
        },
        {
          label: 'Expenses',
          data: labels.map(() => expenses),
          backgroundColor: 'rgba(255, 39, 32, 0.5)',
        },
      ],
    };
  };


  useEffect(() => {
    setBar(getData(props.income, props.expense));
  }, [props, props.expense, props.income]);


  const options = {
    responsive: true,
    animation: {
      duration: 50
    },
    interaction: {
      intersect: false,
      mode: '',
    },
    tooltip: {
      enabled: false
    },
    plugins: {
      legend: {
        position: 'top',
      },
      title: {
        display: false,
        text: 'Chart.js Bar Chart',
      },
    },
  };
  

  return (
    <Bar 
      options={options} 
      data={bar} 
      width={"60rem"}
      height={"20rem"}
    />);
}
