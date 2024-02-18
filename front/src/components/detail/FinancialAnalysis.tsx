import AnalysisTitle from './AnalysisTitle';
import Chart from './Chart';

interface Iprops {
  companyId: string;
  analysisList: Array<any>;
}

export default function FinancialAnalysis({ analysisList }: Iprops) {
  return (
    <>
      <div className="flex flex-col">
        <div className="flex flex-wrap mx-[1.5vw] mt-30">
          {analysisList.map((analysisItem: any, index) => {
            let width = { chart: '', info: '' };
            if (analysisItem.data.result.length < 4) {
              width.chart = 'w-[45.7%]';
              width.info = 'w-[400px]';
            } else {
              width.chart = 'w-[95.7%]';
              width.info = 'min-w-[600px] max-w-[1000px]';
            }
            return analysisItem.data.exist_all ? (
              <div key={index} className={'flex flex-col mx-[1.5vw] mt-10 ' + width.chart}>
                <AnalysisTitle
                  name={analysisItem.data.analysis_name}
                  rate={analysisItem.data.rate}
                  description={analysisItem.data.analysisInfo.analysis_description}
                  width={width.info}
                />
                {/* 차트 부분 */}
                <div className="h-auto pr-20 mt-10 mb-20 bg-white border-gray-500 rounded-5 border-1">
                  <Chart chartData={analysisItem} />
                </div>
                <div className="mt-20"></div>
              </div>
            ) : (
              ''
            );
          })}
        </div>
      </div>
    </>
  );
}
