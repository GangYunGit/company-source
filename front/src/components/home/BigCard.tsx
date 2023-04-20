import Image from "next/image";

export default function BigCard() {
  return (
    <>
      <div
        className="flex flex-col w-[200px] h-[300px]
        shadow-[0_35px_60px_-15px_rgba(0,0,0,0.3)] rounded-10
      mx-[26px] my-[26px] relative hover:scale-110"
      >
        <div className="absolute bg-brand w-[100%] h-[10px] rounded-tl-5 rounded-tr-5 top-0 left-0"></div>
        <Image
          src="/naver_logo_big.png"
          alt="naver.logo_big.png"
          width={200 - 52}
          height={0}
          className="mx-[26px] my-[50px]"
        />
        <div className="font-bold mx-[26px] mb-[26px] text-20">네이버</div>
        <div>
          <div className="text-gray-400 mx-[26px] w-[260-52px] mb-[25px] text-ellipsis">
            네이버는 대한민국 포털 사이트이다. 검색 엔진 등 포탈 서비스를
            중심으로 블로그, 카페, 포스트등의 커뮤니티 서비스를 비롯...
          </div>
        </div>
      </div>
    </>
  );
}
