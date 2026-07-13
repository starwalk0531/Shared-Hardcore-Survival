# Maven 構建指南

本指南說明如何使用 Maven 將 Shared Hardcore Survival MOD 打包成 .jar 檔案。

## 前置要求

- **Java Development Kit (JDK)**: 版本 17 或以上
- **Maven**: 版本 3.6.0 或以上
- **Git**: 用於克隆儲存庫

### 安裝 Maven

#### Windows
1. 從 [Apache Maven 官網](https://maven.apache.org/download.cgi) 下載 Maven
2. 解壓到任意目錄（例如：`C:\maven`）
3. 添加 Maven 到系統環境變數：
   - 設置 `MAVEN_HOME` = `C:\maven`
   - 在 `PATH` 中添加 `%MAVEN_HOME%\bin`
4. 驗證安裝：
   ```bash
   mvn --version
   ```

#### macOS
```bash
# 使用 Homebrew
brew install maven

# 驗證安裝
mvn --version
```

#### Linux (Ubuntu/Debian)
```bash
# 使用 apt
sudo apt-get install maven

# 驗證安裝
mvn --version
```

## 使用 Maven 構建

### 1. 克隆儲存庫

```bash
git clone https://github.com/starwalk0531/Shared-Hardcore-Survival.git
cd Shared-Hardcore-Survival
```

### 2. 清潔構建

```bash
mvn clean
```

### 3. 編譯代碼

```bash
mvn compile
```

### 4. 運行測試（可選）

```bash
mvn test
```

### 5. 打包成 JAR

```bash
mvn package
```

### 6. 一次性構建（推薦）

```bash
mvn clean compile package
```

## 輸出文件位置

構建完成後，您可以在以下位置找到 JAR 檔案：

```
target/sharedhardcoresurvival-1.0.0.jar
```

如果使用了 Shade Plugin，還會生成：
```
target/sharedhardcoresurvival-1.0.0-shaded.jar
```

## 常用 Maven 命令

| 命令 | 功能 |
|------|------|
| `mvn clean` | 刪除所有編譯文件 |
| `mvn compile` | 編譯源代碼 |
| `mvn test` | 運行單元測試 |
| `mvn package` | 打包成 JAR 檔案 |
| `mvn install` | 安裝到本地倉庫 |
| `mvn deploy` | 部署到遠程倉庫 |
| `mvn clean package -DskipTests` | 跳過測試快速打包 |
| `mvn help:active-profiles` | 查看活動配置文件 |
| `mvn dependency:tree` | 查看依賴樹 |

## 快速打包步驟（Windows）

```bash
# 1. 克隆
git clone https://github.com/starwalk0531/Shared-Hardcore-Survival.git
cd Shared-Hardcore-Survival

# 2. 快速構建（跳過測試）
mvn clean package -DskipTests

# 3. JAR 檔案位置
# target/sharedhardcoresurvival-1.0.0.jar
```

## 快速打包步驟（macOS/Linux）

```bash
# 1. 克隆
git clone https://github.com/starwalk0531/Shared-Hardcore-Survival.git
cd Shared-Hardcore-Survival

# 2. 快速構建（跳過測試）
mvn clean package -DskipTests

# 3. 查看 JAR 文件
ls -lh target/*.jar
```

## 安裝 MOD 到 Minecraft

1. **找到 Minecraft 文件夾：**
   - Windows: `%APPDATA%\.minecraft`
   - macOS: `~/Library/Application Support/minecraft`
   - Linux: `~/.minecraft`

2. **打開 `mods` 文件夾**（不存在則創建）

3. **複製 JAR 文件：**
   ```
   target/sharedhardcoresurvival-1.0.0.jar → mods/
   ```

4. **重新啟動遊戲**
   - 使用 Forge 1.20.1 加載器
   - MOD 應該自動加載

## 常見問題

### Q: Maven 找不到
**A**: 確保已正確安裝 Maven 並添加到系統環境變數。執行 `mvn --version` 驗證。

### Q: 編譯失敗，找不到 Java 17
**A**: 確保已安裝 JDK 17 或更高版本，並設置 `JAVA_HOME` 環境變數。

### Q: 依賴下載很慢
**A**: 可以配置 Maven 使用國內鏡像源，編輯 `~/.m2/settings.xml`：
```xml
<mirror>
    <id>alimaven</id>
    <name>aliyun maven</name>
    <url>http://maven.aliyun.com/nexus/content/groups/public/</url>
    <mirrorOf>central</mirrorOf>
</mirror>
```

### Q: 如何查看更詳細的構建日誌？
**A**: 執行以下命令：
```bash
mvn clean package -X
```

### Q: 如何清除本地 Maven 緩存？
**A**: 執行以下命令（會重新下載所有依賴）：
```bash
mvn clean
rm -rf ~/.m2/repository
mvn package
```

## Gradle vs Maven

| 特性 | Gradle | Maven |
|------|--------|-------|
| 配置文件 | build.gradle | pom.xml |
| 構建速度 | 快 | 中等 |
| 學習曲線 | 較陡 | 溫和 |
| 構建命令 | `./gradlew build` | `mvn clean package` |
| Minecraft MOD | 主流 | 可用 |

## 版本信息

- **MOD 版本**: 1.0.0
- **Minecraft 版本**: 1.20.1
- **Forge 版本**: 47.4.21
- **Java 版本**: 17
- **Maven 版本**: 3.6.0+

## 支持

如有 Maven 構建問題，請檢查：
1. Java 版本是否為 17+
2. Maven 是否正確安裝
3. 網絡連接是否正常（首次構建需要下載依賴）
4. pom.xml 配置是否正確
