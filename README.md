# Bike & Pedestrian Analysis — Somerville, MA (2015–2024)

This project analyzes pedestrian and cyclist trends in Somerville, MA using transportation count data from 2015–2024. The goal was to determine whether pedestrian activity increased significantly compared to cyclists over the decade.

## 🔍 Hypothesis
Pedestrian counts increased significantly compared to cyclists from 2015 to 2024.

## 🛠️ Methods
- Downloaded dataset using `curl`
- Filtered to Year, Mode, and Count columns using `awk`
- Calculated yearly totals for pedestrians and cyclists
- Computed Pedestrian‑to‑Cyclist ratios for each year
- Saved results into a ratio summary file

## 📊 Findings
- Pedestrian activity rose slightly and peaked in 2021  
- After 2021, counts declined  
- The Pedestrian‑to‑Cyclist ratio did **not** show a sustained increase  

**Conclusion:** The hypothesis was disproven.

## 📁 Files
- [`selected-bike-ped.csv`](https://github.com/DarcyH2024/Bike-Pedestrian-Analysis/raw/refs/heads/main/selected-bike-ped.csv) — filtered dataset  
- `pedestrian-to-cyclist-ratio.csv` — yearly ratios  
