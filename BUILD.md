# 構建指南

本指南說明如何將 Shared Hardcore Survival MOD 打包成 .jar 檔案。

## 前置要求

- **Java Development Kit (JDK)**: 版本 17 或以上
- **Git**: 用於克隆儲存庫
- **Gradle**: 項目已包含 Gradle Wrapper（無需單獨安裝）

## 構建步驟

### 1. 克隆儲存庫

```bash
git clone https://github.com/starwalk0531/Shared-Hardcore-Survival.git
cd Shared-Hardcore-Survival
```

### 2. 構建 MOD

#### 在 Windows 上：
```bash
gradlew.bat build
```

#### 在 macOS/Linux 上：
```bash
chmod +x gradlew
./gradlew build
```

### 3. 等待構建完成

構建過程通常需要 3-5 分鐘，Gradle 會下載必要的依賴項。

## 輸出文件位置

構建完成後，您可以在以下位置找到 JAR 檔案：

```
build/libs/sharedhardcoresurvival-1.0.0.jar
```

## 安裝 MOD

1. 找到您的 Minecraft 文件夾：
   - **Windows**: `%APPDATA%\.minecraft`
   - **macOS**: `~/Library/Application Support/minecraft`
   - **Linux**: `~/.minecraft`

2. 找到 `mods` 文件夾（如果不存在則創建）

3. 將 `sharedhardcoresurvival-1.0.0.jar` 複製到 `mods` 文件夾

4. 重新啟動 Minecraft Launcher 並使用 Forge 1.20.1 加載遊戲

## 常見問題

### Q: 構建失敗，顯示 "Java not found"
**A**: 確保已安裝 JDK 17 或更高版本，並且 Java 路徑已添加到系統環境變數。

### Q: 如何清潔構建（刪除所有編譯文件）？
**A**: 執行以下命令：
```bash
./gradlew clean build
```

### Q: 如何查看構建詳細信息？
**A**: 執行以下命令以獲取更詳細的輸出：
```bash
./gradlew build --debug
```

### Q: 如何構建開發環境（IDE 使用）？
**A**: 執行以下命令來生成 IDE 配置文件：

#### 對於 Eclipse：
```bash
./gradlew eclipse
```

#### 對於 IntelliJ IDEA：
```bash
./gradlew idea
```

## 構建選項

### 僅構建 JAR，跳過測試：
```bash
./gradlew build -x test
```

### 創建可運行的開發環境：
```bash
./gradlew runClient
```

## 版本信息

- **MOD 版本**: 1.0.0
- **Minecraft 版本**: 1.20.1
- **Forge 版本**: 47.4.21
- **Java 版本**: 17

## 支持

如有構建問題，請檢查：
1. Java 版本是否為 17+
2. Gradle Wrapper 是否有執行權限（Linux/Mac）
3. 網絡連接是否正常（首次構建需要下載依賴）
