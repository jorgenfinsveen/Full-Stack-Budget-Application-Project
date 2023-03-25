import React from 'react';
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

export const options = {
  responsive: true,
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

const labels = ['category 1', 'category 2'];

export const data = {
  labels,
  datasets: [
    {
      label: 'Income',
      data: labels.map(() => 200),
      backgroundColor: 'rgba(99, 255, 32, 0.5)',
    },
    {
      label: 'Expenses',
      data: labels.map(() => 450),
      backgroundColor: 'rgba(255, 39, 32, 0.5)',
    },
  ],
};

export function BarChart() {
  return (
    <Bar 
      options={options} 
      data={data} 
      width={"60rem"}
      height={"20rem"}
    />);
}
