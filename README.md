# 原代碼有誤請直接下載.jar檔案


# Shared Hardcore Survival MOD

一個為 Minecraft 1.20.1 Forge 開發的多人共享硬核生存模組。

## 功能特性

### 1. 玩家等待系統
- 等待 4 位玩家加入
- HUD 顯示目前人數（0/4～4/4）
- 記錄玩家名稱與 UUID

### 2. 開局系統
- 隨機分配四位玩家至地圖四個角落
- 每位玩家位置不可重複
- 自動傳送玩家
- 確認四人皆到位後開始挑戰

### 3. Hardcore 系統
- 任一玩家死亡，全隊立即死亡
- 挑戰結束

### 4. 共享普通背包
- 四位玩家共用一個普通背包
- 放入、拿出、查看皆同步

### 5. 箱子共享
- 所有箱子內容完全同步
- 任一玩家操作立即同步給其他玩家

### 6. 血量共享
- 任一玩家受到傷害，全體玩家同步扣血
- 任一玩家回血，全體玩家同步回血

### 7. 飢餓共享
- 飽食度同步
- 飽和度同步
- 任一玩家吃東西，全體同步恢復

### 8. HUD 顯示
- 顯示等待玩家人數
- 顯示生存時間
- 顯示挑戰結束資訊

### 9. 管理指令
- `/shared start` - 開始挑戰
- `/shared reset` - 重置遊戲
- `/shared end` - 結束挑戰
- `/shared info` - 顯示遊戲信息
- `/shared bag` - 打開共享背包

## 不共享內容
- Sophisticated Backpacks 背包
- 護甲欄
- 副手
- Curios 欄位
- Enigmatic Legacy（神秘遺物）能力

## 開發環境要求

- **Minecraft**: 1.20.1
- **Forge**: 47.4.21
- **Java**: 17

## 安裝方法

1. 將 MOD 文件放入 `mods` 文件夾
2. 啟動遊戲
3. 在主菜單中應能看到 MOD 加載確認

## 使用方法

1. 創建一個新世界或進入現有世界
2. 等待 4 位玩家加入（可以是本地或遠程）
3. 使用 `/shared start` 開始挑戰
4. 挑戰開始後，所有數據將同步
5. 如果任一玩家死亡，全隊失敗

## 文件結構

```
src/main/
├── java/com/starwalk/sharedhardcoresurvival/
│   ├── SharedHardcoreSurvival.java        # 主入口
│   ├── game/
│   │   ├── GameState.java                 # 遊戲狀態管理
│   │   ├── SharedStats.java               # 共享統計數據
│   │   ├── SharedInventory.java           # 共享背包
│   │   └── SpawnPoint.java                # 生成點定義
│   ├── command/
│   │   └── SharedCommand.java             # 指令系統
│   ├── event/
│   │   └── PlayerEventHandler.java        # 玩家事件處理
│   ├── network/
│   │   ├── PacketHandler.java             # 網路包處理
│   │   ├── SyncGameStatePacket.java       # 遊戲狀態同步包
│   │   ├── SyncSharedStatsPacket.java     # 統計同步包
│   │   └── SyncInventoryPacket.java       # 背包同步包
│   └── client/
│       └── HudRenderer.java               # HUD 渲染
└── resources/
    ├── META-INF/mods.toml                 # MOD 元數據
    └── pack.mcmeta                        # 資源包配置
```

## 開發進度

- [x] 遊戲狀態管理
- [x] 玩家註冊系統
- [x] 共享統計系統
- [x] 共享背包系統
- [x] 網路同步系統
- [x] 指令系統
- [x] HUD 渲染
- [ ] 玩家傳送系統
- [ ] 箱子同步系統
- [ ] 背包 UI
- [ ] 配置文件系統

## 許可證

MIT License

## 作者

Starwalk
