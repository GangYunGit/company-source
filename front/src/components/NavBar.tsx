import Image from "next/image";
import { useRouter } from "next/router";
import Link from "next/link";

export default function NavBar() {
  const router = useRouter();

  return (
    <>
      <div
        className={
          "flex justify-between items-center mx-[10vw] w-[80vw] " +
          `${router.pathname === "/" ? "absolute" : null}`
        }
      >
        <div className="flex flex-row items-center">
          <Link href="/home">
            {/* <div className="relative w-[123px] h-[58px] mx-[3vw]"> */}
            <div>
              {router.pathname === "/" || router.pathname === "/home" ? (
                <Image
                  src="/white_logo.png"
                  alt="white_logo.png"
                  width={123}
                  height={58}
                  className="w-auto mx-[3vw]"
                />
              ) : (
                <Image
                  src="/logo.png"
                  alt="logo.png"
                  width={123}
                  height={58}
                  className="w-auto mx-[3vw]"
                />
              )}
            </div>
          </Link>

          <Link href="/home">
            <div
              className={
                "mx-[3vw] " +
                `${
                  router.pathname === "/"
                    ? "text-white"
                    : `${
                        router.pathname === "/home"
                          ? "text-white font-bold"
                          : "text-gray-400"
                      }`
                }`
              }
            >
              기업 분석
            </div>
          </Link>

          <Link href="/comparison">
            <div
              className={
                "mx-[3vw] " +
                `${
                  router.pathname === "/" || router.pathname === "/home"
                    ? "text-white"
                    : `${
                        router.pathname === "/comparison"
                          ? "text-black font-bold"
                          : "text-gray-400"
                      }`
                }`
              }
            >
              기업 비교
            </div>
          </Link>

          <Link href="/board">
            <div
              className={
                "mx-[3vw] " +
                `${
                  router.pathname === "/board"
                    ? "text-black font-bold"
                    : `${
                        router.pathname === "/" || router.pathname === "/home"
                          ? "text-white"
                          : "text-gray-400"
                      }`
                }`
              }
            >
              커뮤니티
            </div>
          </Link>
        </div>

        {/* User Icon */}
        <div>
          {router.pathname === "/" || router.pathname === "/home" ? (
            <Image
              src="/white_user.png"
              alt="white_user.png"
              width={40}
              height={40}
            />
          ) : (
            <Image src="/user.png" alt="user.png" width={40} height={40} />
          )}
        </div>
      </div>
    </>
  );
}
