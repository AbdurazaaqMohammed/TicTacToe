package io.github.abdurazaaqmohammed.tictactoe;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity {

    private boolean playerXTurn = true;
    private final Button[][] buttons = new Button[3][3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TableLayout tableLayout = new TableLayout(this);
        tableLayout.setPadding(10,10,10,10);
        tableLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        tableLayout.setBackgroundColor(Color.BLACK);
        tableLayout.setGravity(Gravity.CENTER);
        tableLayout.setColumnStretchable(0, true);
        tableLayout.setColumnStretchable(1, true);
        tableLayout.setColumnStretchable(2, true);

        GradientDrawable border = new GradientDrawable();
        border.setColor(Color.BLACK); // Background color
        border.setStroke(5, Color.MAGENTA); // Border width and color
        border.setCornerRadius(16);

        for (int i = 0; i < 3; i++) {
            TableRow tableRow = new TableRow(this);
            for (int j = 0; j < 3; j++) {
                final Button b = buttons[i][j] = new Button(this);
                b.setTextSize(40);
                b.setTextColor(Color.MAGENTA);
                b.setBackgroundDrawable(border);
                b.setLayoutParams(new TableRow.LayoutParams(300, 300, 1.0f));

                int row = i;
                int col = j;
                b.setOnClickListener(v -> {
                    if (TextUtils.isEmpty(buttons[row][col].getText())) {
                        buttons[row][col].setText(playerXTurn ? "X" : "O");
                        boolean result = false;
                        for (int i1 = 0; i1 < 3; i1++) {
                            if (buttons[i1][0].getText().equals(buttons[i1][1].getText()) && buttons[i1][1].getText().equals(buttons[i1][2].getText()) && !TextUtils.isEmpty(buttons[i1][0].getText()) || buttons[0][i1].getText().equals(buttons[1][i1].getText()) && buttons[1][i1].getText().equals(buttons[2][i1].getText()) && !TextUtils.isEmpty(buttons[0][i1].getText())) {
                                result = true;
                                break;
                            }
                        }
                        if (!result) {
                            result = buttons[0][0].getText().equals(buttons[1][1].getText()) && buttons[1][1].getText().equals(buttons[2][2].getText()) && !TextUtils.isEmpty(buttons[0][0].getText()) || buttons[0][2].getText().equals(buttons[1][1].getText()) && buttons[1][1].getText().equals(buttons[2][0].getText()) && !TextUtils.isEmpty(buttons[0][2].getText());
                        }
                        if (result) {
                            Toast.makeText(MainActivity.this, (playerXTurn ? "X" : "O") + " won", Toast.LENGTH_SHORT).show();
                            new Timer().schedule(new TimerTask() {
                                @Override
                                public void run() {
                                    for (int i12 = 0; i12 < 3; i12++) {
                                        for (int j12 = 0; j12 < 3; j12++) {
                                            buttons[i12][j12].setText("");
                                        }
                                    }
                                    playerXTurn = true;
                                }
                            }, 2000);
                        } else {
                            boolean result1 = true;
                            for (int i1 = 0; i1 < 3; i1++) {
                                for (int j1 = 0; j1 < 3; j1++) {
                                    if (TextUtils.isEmpty(buttons[i1][j1].getText())) {
                                        result1 = false;
                                        break;
                                    }
                                }
                                if (!result1) break;
                            }
                            if (result1) {
                                Toast.makeText(MainActivity.this, "Draw", Toast.LENGTH_SHORT).show();
                                new Timer().schedule(new TimerTask() {
                                    @Override
                                    public void run() {
                                        for (int i12 = 0; i12 < 3; i12++) {
                                            for (int j12 = 0; j12 < 3; j12++) {
                                                buttons[i12][j12].setText("");
                                            }
                                        }
                                        playerXTurn = true;
                                    }
                                }, 2000);
                            } else {
                                playerXTurn = !playerXTurn;
                            }
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "Cell already taken", Toast.LENGTH_SHORT).show();
                    }
                });
                tableRow.addView(b);
            }
            tableLayout.addView(tableRow);
        }
        setContentView(tableLayout);
    }
}