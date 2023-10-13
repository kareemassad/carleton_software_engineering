import pandas as pd

# Read the Excel file into a pandas dataframe
df = pd.read_excel("CMAS Nomination Form(1-178).xlsx")

# Group the dataframe by the position being nominated for
grouped = df.groupby("Select the position you are nominating this person for:")

# Loop through each group and output the number of nominations and the names of the nominees
for position, group in grouped:
    num_nominations = len(group)
    nominees = (
        group["Enter the name of your Nomination (Full Name): "]
        .value_counts()
        .reset_index()
    )
    print(f"{position}: {num_nominations} nominations")
    print("Nominees:")
    for i, row in nominees.iterrows():
        name = row["index"]
        count = row["Enter the name of your Nomination (Full Name): "]
        print(f"- {name}: {count} nominations")
    print()
