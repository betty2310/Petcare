`Quản lý hệ thống thú cưng 🐶 🐱`


## Cài đặt

1. Cài đặt IDE (InteliJ IDEA): Tải về và cài đặt theo hướng dẫn tại trang chủ của [InteliJ IDEA](https://www.jetbrains.com/idea/download/).

2. Cài đặt JavaFX và thiết lập trong IDE
    - Tải và cài đặt **JavaFX** tại [GluonHQ](https://gluonhq.com/products/javafx/).
    - Thêm ***Environment Variables***:
      - **Windows**: ```set PATH_TO_FX="path\to\javafx-sdk-15.0.1\lib"```
      - **MacOS/Linux**:
          `export PATH_TO_FX=path/to/javafx-sdk-15.0.1/lib`
      
          Để JavaFX tự động chạy khi khởi động Linux, hãy thêm câu lệnh trên vào `~/.bashrc`.
      - Thêm vào **configuration**:
          Mở IDE (Inntellij), vào **Run**, chọn **Edit Configuration**, chọn **Add VM variables** và thêm:
            
            --module-path $PATH_TO_FX --add-modules javafx.controls,javafx.fxml`

## Lisence

+ MIT