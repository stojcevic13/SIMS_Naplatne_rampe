package view.leader;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


import manage.ManagerFactory;
import users.Leader;

public class LeaderProfile extends JPanel{

	private static final long serialVersionUID = 7193559629164883549L;
		private ManagerFactory mngFactory;
		private Leader leader;
		private JFrame contentPane;
	
		LeaderProfile(ManagerFactory mngFactory, Leader leader, JFrame contentPane){
		this.mngFactory = mngFactory;
		this.leader = leader;
		this.contentPane = contentPane;
		
		this.setLayout(new GridBagLayout());
		Insets insets = new Insets(10, 10, 10, 10);
		
		JLabel lblTitle = new JLabel("Your profile");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints conTitle = new GridBagConstraints();
		conTitle.insets = insets;
		conTitle.gridx = 0;
		conTitle.gridy = 0;
		conTitle.anchor = GridBagConstraints.NORTHWEST;
		conTitle.gridwidth = 1;
		conTitle.gridheight = 1;
		this.add(lblTitle, conTitle);
		
		JButton changeBtn  = new JButton("Change profile");
		GridBagConstraints conChange = new GridBagConstraints();
		conChange.insets = insets;
		conChange.gridx = 1;
		conChange.gridy = 0;
		conChange.anchor = GridBagConstraints.NORTHWEST;
		conChange.gridwidth = 1;
		conChange.gridheight = 1;
		this.add(changeBtn , conChange);
		
		JLabel lblFirstName = new JLabel("First name");
		lblFirstName.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints conFirstName = new GridBagConstraints();
		conFirstName.gridy = 1;
		conFirstName.gridx = 0;
		conFirstName.anchor = GridBagConstraints.NORTHWEST;
		conFirstName.gridheight = 1;
		conFirstName.gridwidth = 1;
		conFirstName.insets = insets;
		this.add(lblFirstName, conFirstName);
		
		JLabel lblFirstNameUser = new JLabel(this.leader.getFirstName());
		lblFirstNameUser.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints conFirstNameUser = new GridBagConstraints();
		conFirstNameUser.gridy = 1;
		conFirstNameUser.gridx = 1;
		conFirstNameUser.anchor = GridBagConstraints.NORTHWEST;
		conFirstNameUser.gridheight = 1;
		conFirstNameUser.gridwidth = 1;
		conFirstNameUser.insets = insets;
		this.add(lblFirstNameUser, conFirstNameUser);
		
		JLabel lblLastName = new JLabel("Last name");
		lblLastName.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints conLastName = new GridBagConstraints();
		conLastName.insets = insets;
		conLastName.gridx = 0;
		conLastName.gridy = 2;
		conLastName.anchor = GridBagConstraints.NORTHWEST;
		conLastName.gridwidth = 1;
		conLastName.gridheight = 1;
		this.add(lblLastName, conLastName);
		
		JLabel lblLastNameUser = new JLabel(this.leader.getLastName());
		lblLastNameUser.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints conLastNameUser = new GridBagConstraints();
		conLastNameUser.insets = insets;
		conLastNameUser.gridx = 1;
		conLastNameUser.gridy = 2;
		conLastNameUser.anchor = GridBagConstraints.NORTHWEST;
		conLastNameUser.gridwidth = 1;
		conLastNameUser.gridheight = 1;
		this.add(lblLastNameUser, conLastNameUser);
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints conGender = new GridBagConstraints();
		conGender.insets = insets;
		conGender.gridx = 0;
		conGender.gridy = 3;
		conGender.anchor = GridBagConstraints.NORTHWEST;
		conGender.gridwidth = 1;
		conGender.gridheight = 1;
		this.add(lblGender, conGender);
		
		JLabel lblGenderUser = new JLabel(this.leader.getGender().name());
		lblGenderUser.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints conGenderUser = new GridBagConstraints();
		conGenderUser.insets = insets;
		conGenderUser.gridx = 1;
		conGenderUser.gridy = 3;
		conGenderUser.anchor = GridBagConstraints.NORTHWEST;
		conGenderUser.gridwidth = 1;
		conGenderUser.gridheight = 1;
		this.add(lblGenderUser, conGenderUser);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints conAddress = new GridBagConstraints();
		conAddress.insets = insets;
		conAddress.gridx = 0;
		conAddress.gridy = 4;
		conAddress.anchor = GridBagConstraints.NORTHWEST;
		conAddress.gridheight = 1;
		conAddress.gridwidth = 1;
		this.add(lblAddress, conAddress);
		
		JLabel lblAddressUser = new JLabel(this.leader.getAddress());
		lblAddressUser.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints conAddressUser = new GridBagConstraints();
		conAddressUser.insets = insets;
		conAddressUser.gridx = 1;
		conAddressUser.gridy = 4;
		conAddressUser.anchor = GridBagConstraints.NORTHWEST;
		conAddressUser.gridheight = 1;
		conAddressUser.gridwidth = 1;
		this.add(lblAddressUser, conAddressUser);
		
		JLabel lblUsername = new JLabel("User name");
		lblUsername.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints conUsername = new GridBagConstraints();
		conUsername.insets = insets;
		conUsername.gridx = 0;
		conUsername.gridy = 5;
		conUsername.anchor = GridBagConstraints.NORTHWEST;
		conUsername.gridheight = 1;
		conUsername.gridwidth = 1;
		this.add(lblUsername, conUsername);
		
		JLabel lblUsernameUser = new JLabel(this.leader.getUsername());
		lblUsernameUser.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints conUsernameUser = new GridBagConstraints();
		conUsernameUser.insets = insets;
		conUsernameUser.gridx = 1;
		conUsernameUser.gridy = 5;
		conUsernameUser.anchor = GridBagConstraints.NORTHWEST;
		conUsernameUser.gridheight = 1;
		conUsernameUser.gridwidth = 1;
		this.add(lblUsernameUser, conUsernameUser);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints conPassword = new GridBagConstraints();
		conPassword.insets = insets;
		conPassword.gridx = 0;
		conPassword.gridy = 6;
		conPassword.anchor = GridBagConstraints.NORTHWEST;
		conPassword.gridheight = 1;
		conPassword.gridwidth = 1;
		this.add(lblPassword, conPassword);
		
		JLabel lblPasswordUser = new JLabel(this.leader.getPassword());
		lblPasswordUser.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints conPasswordUser = new GridBagConstraints();
		conPasswordUser.insets = insets;
		conPasswordUser.gridx = 1;
		conPasswordUser.gridy = 6;
		conPasswordUser.anchor = GridBagConstraints.NORTHWEST;
		conPasswordUser.gridheight = 1;
		conPasswordUser.gridwidth = 1;
		this.add(lblPasswordUser, conPasswordUser);
		
		contentPane.add(this);
		/*
		changeBtn.addActionListener(new ActionListener() {
	
			@Override
			public void actionPerformed(ActionEvent e) {
				PredavacChangeProfila ChangeDijalog = new PredavacChangeProfila(mngFactory, User,
						lblFirstNameUser, lblPrezFirstNameUser, lblGenderUser, lblAddressUser,
						 lblPasswordUser, lblPhoneUser, lblDatumRodjenjaUser);
	
				}});
		
	*/	
	}
}