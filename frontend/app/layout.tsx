import "bootstrap/dist/css/bootstrap.min.css";
import Header from "../components/Header/Header";

import './index.css';

export const metadata = {
  title: {
    template: '%s | Todo',
    default: 'Todo'
  },
  description: '그냥 만든 todo',
}

export default function RootLayout({
  children,
}: {
  children: React.ReactNode
}) {
  return (
    <html lang="ko">
      <head>
        <link href='//spoqa.github.io/spoqa-han-sans/css/SpoqaHanSansNeo.css' rel='stylesheet' type='text/css' />
      </head>
      <body>
        <Header />  
        {children}
      </body>
    </html>
  )
}
