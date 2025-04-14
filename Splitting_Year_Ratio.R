data <- read.csv("pedestrian-to-cyclist-ratio.csv", header = TRUE)

# Print to check the structure
print(data)

# Install and load tidyr (if not already installed)
install.packages("tidyr")
library(tidyr)

# Split the column into Year and Ratio
data <- data %>%
  separate(Year.Pedestrian.to.Cyclist.Ratio, into = c("Year", "Ratio"), sep = " ")

# Convert columns to appropriate types
data$Year <- as.integer(data$Year)
data$Ratio <- as.numeric(data$Ratio)

# Verify the new structure of the data
print(data)