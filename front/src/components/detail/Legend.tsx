interface Iprops {
  compare: string,
}

export default function Legend({ compare }: Iprops) {
  return (
    <>
      <div className="flex justify-center mb-20">
        <div className="flex items-center">
          <div className="bg-[#8884d8] w-10 h-8" />
          <span className="ml-5 mr-10 text-12">{compare}</span>
        </div>
        <div className="flex items-center">
          <div className="w-10 h-8 bg-[#efad45]" />
          <span className="ml-5 mr-10 text-12">보통</span>
        </div>
        <div className="flex items-center">
          <div className="w-10 h-8 bg-[#ff0000]" />
          <span className="ml-5 mr-10 text-12">불량</span>
        </div>
        <div className="flex items-center">
          <div className="bg-[#82ca9d] w-10 h-8" />
          <span className="ml-5 text-12">양호</span>
        </div>
      </div>
    </>
  )
}