import React, { useEffect, useState } from 'react';
import { BarChart, Bar, XAxis, YAxis, CartesianGrid, Tooltip, ReferenceLine } from 'recharts';
import Legend from './Legend';

interface Iprops {
  chartData: any;
}

export default function Chart({ chartData }: Iprops) {
  const [data, setData] = useState<any>();

  useEffect(() => {
    setData(chartData);
  }, []);

  return (
    <>
      <div>
        <div className="flex justify-center flex-nowrap text-12">
          {data
            ? data.data.result.map((item: any, index: number) => {
                return (
                  <div key={index}>
                    <BarChart
                      width={150}
                      height={300}
                      data={[item]}
                      margin={{
                        top: 40,
                        right: 30,
                        left: 0,
                        bottom: 40,
                      }}
                    >
                      <CartesianGrid strokeDasharray="3 3" />
                      <XAxis dataKey="name" tickMargin={16} interval={1} />
                      <YAxis />
                      <Tooltip wrapperStyle={{ zIndex: '50' }} />
                      <ReferenceLine y={0} stroke="#000" />
                      <Bar
                        dataKey={data.data.corp_name}
                        fill={item['평가'] === '양호' ? '#82ca9d' : item['평가'] === '불량' ? 'red' : '#efad45'}
                      />
                      <Bar dataKey="산업평균" fill="#8884d8" />
                    </BarChart>
                  </div>
                );
              })
            : '데이터가 없어요 ㅠㅠ'}
        </div>
        <Legend compare="산업평균" />
      </div>
    </>
  );
}
